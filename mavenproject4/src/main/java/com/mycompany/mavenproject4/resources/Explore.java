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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
@Path("/explore")
public class Explore {
    
    
    @GET
    @Produces("application/json")
    public Response main() {

        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readFromCSV();
        
        return  Response.status(200).entity(pyramids).build();
        }
    
    @Path("{f}")
    @GET
    @Produces("application/json")
    public Response main(@PathParam("f") int f) {

        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readFromCSV();
        
        return  Response.status(200).entity(pyramids.get(f)).build();
        }
    }

