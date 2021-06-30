/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.desc;
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
    
    
}
