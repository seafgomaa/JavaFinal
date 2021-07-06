/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject4.resources;

/**
 *
 * @author amer
 */
public class Pyramid {
    private String pharaoh, modern_name, site;
    private double height;

    public Pyramid(String pharaoh, String modern_name, String site, double height) {
        this.pharaoh = pharaoh;
        this.modern_name = modern_name;
        this.site = site;
        this.height = height;
    }

    public String getPharaoh() {
        return this.pharaoh;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public String getModern_name() {
        return this.modern_name;
    }

    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String info() {
        return this.pharaoh + "," + this.modern_name + "," + this.site + "," + this.height;
    }
}
