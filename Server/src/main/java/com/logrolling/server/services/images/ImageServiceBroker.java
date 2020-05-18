package com.logrolling.server.services.images;

import com.logrolling.server.services.authentication.AuthenticationService;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("images")
@RequestScoped
public class ImageServiceBroker {

    //TODO: Refactor

    @Inject
    ServletContext context;

    @GET
    @Path("user/{user}")
    public Response userImage(@PathParam("user") final String user) {
        InputStream resource = context.getResourceAsStream(String.format("/images/user/%s.jpg", user));

        if (resource == null) {
            //No custom photo, return default photo
            InputStream defaultResource = context.getResourceAsStream("/images/default/user.jpg");
            return Response.ok().type("image/jpeg").entity(defaultResource).build();
        } else {
            //Found
            return Response.ok().type("image/jpeg").entity(resource).build();
        }
    }

    @GET
    @Path("unchecked/favor/{favor}")
    public Response uncheckedFavorImage(@PathParam("favor") final String favor) {
        InputStream resource = context.getResourceAsStream(String.format("/images/favor/%s.jpg", favor));
        if (resource == null) {
            //Return 404
            return Response.status(NOT_FOUND).build();
        } else {
            //Found
            return Response.ok().type("image/jpeg").entity(resource).build();
        }

    }

    @GET
    @Path("favor/{favor}")
    public Response favorImage(@PathParam("favor") final String favor) {
        InputStream resource = context.getResourceAsStream(String.format("/images/favor/%s.jpg", favor));

        if (resource == null) {
            //No custom photo, return default photo
            InputStream defaultResource = context.getResourceAsStream("/images/default/favor.jpg");
            return Response.ok().type("image/jpeg").entity(defaultResource).build();
        } else {
            //Found
            return Response.ok().type("image/jpeg").entity(resource).build();
        }
    }

    @GET
    @Path("gift/{gift}")
    public Response giftsImage(@PathParam("gift") final String gift) {
        InputStream resource = context.getResourceAsStream(String.format("/images/gift/%s.jpg", gift));

        if (resource == null) {
            //No custom photo, return default photo
            InputStream defaultResource = context.getResourceAsStream("/images/default/gift.jpg");
            return Response.ok().type("image/jpeg").entity(defaultResource).build();
        } else {
            //Found
            return Response.ok().type("image/jpeg").entity(resource).build();
        }
    }

    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.WILDCARD)
    @Path("user")
    public Response uploadUserImage(@Context HttpServletRequest request, byte[] input, @HeaderParam("token") String token)
    {
        String username = AuthenticationService.authenticateWithToken(token);
        String realPath = context.getRealPath(String.format("/images/user/%s.jpg", username));

        //Save input to realPath
        try {
            FileOutputStream stream = new FileOutputStream(realPath);
            stream.write(input);

            stream.close();
        } catch (IOException e) {
            return Response.serverError().build();
        }

        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.WILDCARD)
    @Path("favor/{favorId}")
    public Response uploadUserImage(@Context HttpServletRequest request, byte[] input, @PathParam("favorId") int favorId)
    {
        String realPath = context.getRealPath(String.format("/images/favor/%d.jpg", favorId));

        //Save input to realPath
        try {
            FileOutputStream stream = new FileOutputStream(realPath);
            stream.write(input);

            stream.close();
        } catch (IOException e) {
            return Response.serverError().build();
        }

        return Response.ok().build();
    }

}
