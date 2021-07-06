/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test2;



import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.desc;
import static org.apache.spark.sql.functions.lit;

/**
 *
 * @author iTs
 */
public class Main {
    
   public static void main(String [] args){
    DAO da = new DAO();
    Operations op=new Operations();
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
   
}
