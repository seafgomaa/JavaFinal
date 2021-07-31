package com.example.demo;

public class POJO {
    
    String Title;
    String  Company;
    String Location;
    String  Type;
    String  Level;
    float  minYearsExp;
    float  maxYearsExp;
    String Country;
    String [] Skills;

    public POJO(String Title, String Company, String Location, String Type, String Level, float minYearsExp, float maxYearsExp, String Country, String[] Skills) {
        this.Title = Title;
        this.Company = Company;
        this.Location = Location;
        this.Type = Type;
        this.Level = Level;
        this.minYearsExp = minYearsExp;
        this.maxYearsExp = maxYearsExp;
        this.Country = Country;
        this.Skills = Skills;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public float getMinYearsExp() {
        return minYearsExp;
    }

    public void setMinYearsExp(float minYearsExp) {
        this.minYearsExp = minYearsExp;
    }

    public float getMaxYearsExp() {
        return maxYearsExp;
    }

    public void setMaxYearsExp(float maxYearsExp) {
        this.maxYearsExp = maxYearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String[] getSkills() {
        return Skills;
    }

    public void setSkills(String[] Skills) {
        this.Skills = Skills;
    }

    @Override
    public String toString() {
        return "POJO{" + "Title=" + Title + ", Company=" + Company + ", Location=" + Location + ", Type=" + Type + ", Level=" + Level + ", minYearsExp=" + minYearsExp + ", maxYearsExp=" + maxYearsExp + ", Country=" + Country + ", Skills=" + Skills + '}';
    }
    
}
