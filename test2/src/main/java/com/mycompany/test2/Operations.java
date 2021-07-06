/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author iTs
 */
public class Operations {
    
     final SparkSession sc = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
    
     public void showsummary(Dataset<Row> ds)
     {
        System.out.println("Data sample: ");
        ds.show(100);
        System.out.println("Data Schema: ");
        ds.printSchema ();
        System.out.println("Data Summary: ");
        ds.describe(ds.columns()).show();
     
     }
     
     public Dataset<Row> cleanData(Dataset<Row> ds)
     {
        ds=ds.dropDuplicates();
        ds=ds.na().drop();
        System.out.println("Data Summary after dropping duplications and na: ");
        return ds;    
     }
     
     public Dataset<String> wordExtractor(Dataset<Row> ds)
     {
        List<String> ls=toList(ds);
        List<String> words=new ArrayList<String>();
        for (int i =0;i<ls.size();i++)
        {
            String [] a=ls.get(i).split(",");
            words.addAll(Arrays.asList(a));
        }
        return toDataset(words);

    }
     
    public List<List<Double>> expSeparator(Dataset<Row> ds)
     {
        List<String> ls=toList(ds);
        List<List<Double>> exp=new ArrayList<List<Double>>();
        List<Double> min= new ArrayList<Double>();
        List<Double> max= new ArrayList<Double>();
        for (int i =0;i<ls.size();i++)
        {
            String [] a=splitter(ls.get(i));
            min.add(toDouble(a[0]));
            max.add(toDouble(a[1]));
            
        }
        exp.add(min);
        exp.add(max);
        
        return exp;
    }
    
    public void graphPiChart(Dataset<Row> ds) {
        //filter to get a map of passenger class and total number of passengers in each clas
        List<String> ls = ds.map(row -> row.mkString(), Encoders.STRING()).collectAsList();
        Map<String, Long> counts =
        ls.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        // Create Chart
        PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160),new Color (170, 30, 20), new Color (110,50,24), new Color (120,25,32)};
        chart.getStyler ().setSeriesColors (sliceColors);
        // Series        
        counts.forEach((k,v)->chart.addSeries (String.valueOf(k),v));
        // Show it
        new SwingWrapper (chart).displayChart ();
    }
    
    public void graphBarChart(Dataset<Row> ds) {
        //filter to get an array of passenger ages
        List<String> ls = ds.map(row -> row.mkString(), Encoders.STRING()).collectAsList();
        Map<String, Long> counts =
        ls.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<Long> y = new ArrayList<Long>(counts.values());
        List<String> x = new ArrayList<String>(counts.keySet());
        //Using XCart to graph the Ages
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder ().width (1024).height (768).title ("Histogram Chart").xAxisTitle ("x").yAxisTitle ("y").build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries ("Histogram chart", x, y);
        // Show it
        new SwingWrapper (chart).displayChart ();
    }
    
    // convert list to dataset    
    public Dataset<String> toDataset(List<String> ls){
      return sc.createDataset(ls, Encoders.STRING());
    }
    
    public Dataset<Double> toDDataset(List<Double> ls){
      return sc.createDataset(ls, Encoders.DOUBLE());
    } 
    
    // convert dataset to list
    public List<String> toList(Dataset<Row> ds){
     return  ds.map(row -> row.mkString(), Encoders.STRING()).collectAsList();
    }
    
    
    
    // Split Experience level into two fields
    public String[] splitter(String x){
        
        try
        {
            if (!x.contains("+")){
            String []a=new String[2];
            a=x.split("-");
            
            if(a.length==2){a[1]=a[1].substring(0, 1);}
            else{a[1]="";}

            
            return a;}
            else {
            return x.split("\\+");
              }
        
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            String [] b={"",""};
            return b; }
    }
   
    //from string to double
    public double toDouble(String x){
        
        try
        {
            return Integer.parseInt(x);
        }
        catch(NumberFormatException e)
        {
            return 0;     
        }
    }
    
}
