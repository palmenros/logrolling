package com.logrolling.server.services.images;

import org.glassfish.jersey.process.internal.RequestScoped;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.WILDCARD)
@Produces(MediaType.WILDCARD)
@Path("images")
@RequestScoped
public class ImageServiceBroker {

    @GET
    @Path("user/{user}")
    public Response userImage(@PathParam("user") final String user) {
        return Image.userImage(user);
    }

    @GET
    @Path("unchecked/favor/{favor}")
    public Response uncheckedFavorImage(@PathParam("favor") final String favor) {
        return Image.uncheckedFavorImage(favor);
    }

    @GET
    @Path("favor/{favor}")
    public Response favorImage(@PathParam("favor") final String favor) {
        return Image.favorImage(favor);
    }

    @GET
    @Path("gift/{gift}")
    public Response giftsImage(@PathParam("gift") final String gift) {
        return Image.giftsImage(gift);
    }

    @POST
    @Path("user")
    public Response uploadUserImage(@Context HttpServletRequest request, byte[] input, @HeaderParam("token") String token) {
        return Image.uploadUserImage(request, input, token);
    }

    @POST
    @Path("favor/{favorId}")
    public Response uploadUserImage(@Context HttpServletRequest request, byte[] input, @PathParam("favorId") int favorId) {
        return Image.uploadUserImage(request, input, favorId);
    }

}
