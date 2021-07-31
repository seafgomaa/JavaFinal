package com.example.demo;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.Serializable;

import static org.apache.spark.sql.functions.*;

@RestController
public class Main implements Serializable {
    public DAO da = new DAO();
    public Operations op=new Operations();
    //Reading The data
    public Dataset<Row> ds = da.readcsv("Wuzzuf_Jobs.csv");



    @GetMapping("/t1")
    public List<Job> main1() throws IOException {

        //Drop duplicates
        ds=op.cleanData(ds);
        Dataset<Row> x =  ds.groupBy(col("Company"))
                .count()
                .sort(desc("count"));
        List<Job> jobs= op.createJobs(x,"Company");
        graph( jobs,"t1","Company");
        return jobs;
    }
    @GetMapping("/t2")
    public List<Job> main2() throws IOException {

        //Drop duplicates

        Dataset<Row> x =   ds.groupBy(col("Title"))
                .count()
                .sort(desc("count"))
                 ;
        List<Job> jobs= op.createJobs(x,"Title");
        graph( jobs,"t2","Titles");
        return jobs;
    }
    @GetMapping("/t3")
    public List<Job> main3() throws IOException {

        //Drop duplicates
        ds=op.cleanData(ds);
        Dataset<Row> x =  op.wordExtractor(ds.select(col("Skills"))).groupBy(col("value"))
                .count()
                .sort(desc("count"))
                ;
        List<Job> jobs= op.createJobs(x,"value");
        graph( jobs,"t3","Skills");
        return jobs;
    }
    @GetMapping("/t4")
    public List<Job> main4() throws IOException {

        //Drop duplicates
        ds=op.cleanData(ds);
        Dataset<Row> x =  ds.groupBy(col("Country"))
                .count()
                .sort(desc("count"));
        List<Job> jobs= op.createJobs(x,"Country");
        graph( jobs,"t4","Country");
        return jobs;
    }

    @GetMapping("/sum")
    public List<String[]> main6() {
        ArrayList<String> ar = new ArrayList<String>();
        String[] allColumnNames = ds.columns();
        ar.add( "Stats");
        for(String i : allColumnNames){
        ar.add(i);
        };
        ds=op.cleanData(ds);
        String[] ColumnNames= ar.toArray(new String[0]);
         op.showsummary(ds).show();
        List<String[]> y = op.toStringList(ds);
        y.add(0,ColumnNames);


        return y;
    }
    public void graph(List<Job> jobs,String filename,String title) throws IOException {
        op.graphPiChart(jobs, "Pie"+filename,title);
        op.graphBarChart( jobs,"bar"+filename,title);
    }

    }






