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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/DAO")
public class DAO {
	@GET
	@Produces("application/xml")
	public String PrintHello() {
                double cel=4;
		String result = "Hello Mohamed";
		return "<ctofservice>" + "<celsius>" + cel + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") String c) {		
 
		
		return "<celsius>" + "Hello "+ c + "</celsius>";
	}
}
