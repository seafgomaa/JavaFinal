package POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Job {
    private String rank;
    private String name,fieldname;
    private String count;

    public Job() {

    }

    public Job(String rank, String name, String fieldname, String count) {
        this.rank = rank;
        this.name = name;
        this.fieldname = fieldname;
        this.count = count;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
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

    public String getCount() {
        return count;
    }

    public void setCount(String  count) {
        this.count =  count;
    }

    @Override
    public String toString() {
        return  "rank = " + this.rank +", "+  this.fieldname+" = "+ this.name + '\'' + ", count = " + this.count ;
    }}
