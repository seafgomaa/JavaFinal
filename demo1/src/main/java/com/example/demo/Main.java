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

@RestController
public class Main implements Serializable {
   @GetMapping("/sql")
   public void main() {
       BasicConfigurator.configure();
       Operations op=new Operations();
       final SparkSession spark = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
       final Dataset<Row> df = spark.read().csv("Wuzzuf_Jobs.csv");
       op.showsummary(df);

}
    /*public void main() {
        BasicConfigurator.configure();
        final SparkSession spark = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
        final Dataset<Row> df = spark.read().csv("pyramids.csv");


    }*/

}
