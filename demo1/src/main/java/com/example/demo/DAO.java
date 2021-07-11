
package com.example.demo;


import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import org.apache.spark.sql.SparkSession;


public class DAO {
 
     final SparkSession sparkSession = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
     
     public Dataset<Row> readcsv(String path){
     
         // Create Spark Session to create connection to Spark
        

        // Get DataFrameReader using SparkSession
        final DataFrameReader dataFrameReader = sparkSession.read ();
        // Set header option to true to specify that first row in file contains
        // name of columns
        
        dataFrameReader.option ("header", "true");
        
        
        
        final Dataset<Row> csvDataFrame = dataFrameReader.csv (path);

        // Print Schema to see column names, types and other metadata
       
        
        
        return csvDataFrame;
       }
     
     
    
        
}
