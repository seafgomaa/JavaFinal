/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test2;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.desc;

/**
 *
 * @author iTs
 */
public class Main {
    
   public static void main(String [] args){
    DAO da = new DAO();
    Operations op=new Operations();
    final SparkSession sc = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
    //Reading The data 
    Dataset<Row> ds = da.readcsv("C:\\Users\\iTs\\Desktop\\Java and UML_Amr Elshafey\\Wuzzuf_Jobs.csv");
    
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
    Dataset<Row> dr=ds.select(col("Skills"));
   
    List<String> ls = dr.map(row -> row.mkString(), Encoders.STRING()).collectAsList(); 
    List<String> skills=new ArrayList<String>();
    for (int i =0;i<ls.size();i++)
    {
        String [] a=ls.get(i).split(",");
        for (int j=0;j<a.length;j++)
        {
            skills.add(a[j]);    
        }
    }
    
    Dataset<String> dataDs = sc.createDataset(skills, Encoders.STRING());
   
    dataDs.groupBy(col("value")) 
            .count()
            .sort(desc("count"))
            .show();
    

     op.graphPiChart(ds.select(col("Company")));
     op.graphBarChart(ds.select("Country"));
     op.graphBarChart(ds.select(col("Title")));

   } 
   
}
