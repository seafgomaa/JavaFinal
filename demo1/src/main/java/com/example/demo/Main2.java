package com.example.demo;




import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


import org.apache.log4j.BasicConfigurator;
import org.apache.spark.sql.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.BasicConfigurator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.Serializable;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.desc;

@RestController
public class Main2 implements Serializable {

    @GetMapping("/sql1")
   public Dataset<Row> main() {
        DAO da = new DAO();
        Operations op=new Operations();
        //Reading The data
        Dataset<Row> ds = da.readcsv("Wuzzuf_Jobs.csv");
       // Show summary
       //op.showsummary(ds);

       //Drop duplicates
       //ds=op.cleanData(ds);

       // Show summary after cleaning
       //op.showsummary(ds);
        Dataset<Row> x =  ds.groupBy(col("Company"))
                .count()
                .sort(desc("count"));
        return x;
    }

    }

        /*

       // count the jobs for each title
       ds.groupBy(col("Title"))
               .count()
               .sort(desc("count"))
               .show();

       // count the jobs for each country
       ds.groupBy(col("Country"))
               .count()
               .sort(desc("count"))
               .show();

}*/
    /*public void main() {
        BasicConfigurator.configure();
        final SparkSession spark = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
        final Dataset<Row> df = spark.read().csv("pyramids.csv");


    }*/
/* MAIN PROJECT
  DAO da = new DAO();
    Operations op=new Operations();
    //Reading The data
    Dataset<Row> ds = da.readcsv("Wuzzuf_Jobs.csv");

    // Show summary
    op.showsummary(ds);

    //Drop duplicates
    ds=op.cleanData(ds);

    // Show summary after cleaning
    op.showsummary(ds);


    // Count the jobs for each company
    ds.groupBy(col("Company"))
            .count()
            .sort(desc("count"))
            .show();


    // count the jobs for each title
    ds.groupBy(col("Title"))
            .count()
            .sort(desc("count"))
            .show();

    // count the jobs for each country
    ds.groupBy(col("Country"))
            .count()
            .sort(desc("count"))
            .show();

    // Most requird skills
    op.wordExtractor(ds.select(col("Skills"))).groupBy(col("value"))
            .count()
            .sort(desc("count"))
            .show();


    // experience
    List<List<Double>> exp=op.expSeparator(ds.select(col("YearsExp")));


     op.graphPiChart(ds.select(col("Company")));
     op.graphBarChart(ds.select("Country"));
     op.graphBarChart(ds.select(col("Title")));
     op.graphPiChart(ds.select(col("Company")));
   }
 */


