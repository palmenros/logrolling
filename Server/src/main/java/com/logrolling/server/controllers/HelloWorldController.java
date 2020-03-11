package com.logrolling.server.controllers;

import com.logrolling.server.model.HelloWorldModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class HelloWorldController {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage()  {
        return HelloWorldModel.getMessage();
    }
}
