package com.mycompany.mavenproject4.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("Mohamed")
                .build();
    }
}
