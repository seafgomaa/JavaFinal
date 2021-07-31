package com.example.demo;


public class Job {
    private long rank;
    private String name,fieldname;
    private long count;

    public Job(long rank, String name, String fieldname, long count) {
        this.rank = rank;
        this.name = name;
        this.fieldname = fieldname;
        this.count = count;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public double getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count =  count;
    }

    @Override
    public String toString() {
        return "Job{" + "rank=" + this.rank +", "+  this.fieldname+"="+ this.name + '\'' + ", count=" + this.count + '}';
    }}
