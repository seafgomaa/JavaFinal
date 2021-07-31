package com.example.demo;


import lombok.var;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.knowm.xchart.BitmapEncoder.getBufferedImage;


public class Operations {
    
     final SparkSession sc = SparkSession.builder ().appName ("Spark CSV Analysis Demo").master ("local[2]").getOrCreate ();
    
     public Dataset<Row> showsummary(Dataset<Row> ds)
     {
        System.out.println("Data sample: ");
        ds.show(100);
        System.out.println("Data Schema: ");
        ds.printSchema ();
        System.out.println("Data Summary: ");
        Dataset<Row> ls=  ds.describe(ds.columns());
        return ls;
     }
    public List<String[]> toStringList(Dataset<Row> ds){
        List<Row> ls=ds.describe(ds.columns()).collectAsList();
        List<String[]> l=new ArrayList<String[]>();
        for(int i=0; i<ls.size();i++){
            String line = ls.get(i).toString().trim();
            String[] data=line.split(",");
            l.add(data);
        }
        return l;
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
    public List<Job> createJobs(Dataset<Row> x,String fieldname) {
        List<Row> arrayList;
        arrayList=x.collectAsList();
        List<Job> jobs= new ArrayList<>();

        long rank =1;
        for( var i : arrayList){
            Job j= new Job(rank,(String) i.getAs(fieldname),fieldname, (Long) i.getAs("count"));
            rank++;
            jobs.add(j);
        }
        return jobs;

    }

    public void graphPiChart(List<Job> jobs, String filename,String title) throws IOException {
        //filter to get a map of passenger class and total number of passengers in each clas
        Map<String, Long> counts =new LinkedHashMap<String,Long>();
        for (Job j : jobs) {
            counts.put(j.getName(),((long) j.getCount()));
        }
        // Create Chart
        PieChart chart = new PieChartBuilder ().width (800).height (600).title (title).build ();
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160),new Color (170, 30, 20), new Color (110,50,24), new Color (120,25,32)};
        chart.getStyler ().setSeriesColors (sliceColors);
        // Series        
        counts.forEach((k,v)->chart.addSeries (String.valueOf(k),v));
        // Show it
        BufferedImage bufferedImage = getBufferedImage(chart);
        File file = new File(filename+ ".png" );
        ImageIO.write(bufferedImage,"png",file);
    }
    
    public void graphBarChart(List<Job> jobs,String filename,String title) throws IOException {
        //filter to get an array of passenger ages
        List<Long> y = new ArrayList<Long>();
        List<String> x = new ArrayList<String>();
        for (Job j : jobs) {
            y.add((long) j.getCount());
            x.add(j.getName());
        }
        //Using XCart to graph the Ages
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder ().width (1024).height (768).title (title+" "+"Histogram Chart").xAxisTitle (title).yAxisTitle ("Count").build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries (title, x, y);
        // Show it
        BufferedImage bufferedImage = getBufferedImage(chart);
        File file = new File(filename+ ".png" );
        ImageIO.write(bufferedImage,"png",file);
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
     return  ds.map((MapFunction<Row, String>) row -> row.mkString(), Encoders.STRING()).collectAsList();
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
