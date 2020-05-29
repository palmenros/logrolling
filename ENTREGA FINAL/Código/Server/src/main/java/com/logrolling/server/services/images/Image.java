package com.logrolling.server.services.images;

import com.logrolling.server.services.authentication.AuthenticationService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class Image {

    public static Response userImage(final String user, ServletContext context) {
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

    public static Response uncheckedFavorImage(final String favor, ServletContext context) {
        InputStream resource = context.getResourceAsStream(String.format("/images/favor/%s.jpg", favor));
        if (resource == null) {
            //Return 404
            return Response.status(NOT_FOUND).build();
        } else {
            //Found
            return Response.ok().type("image/jpeg").entity(resource).build();
        }

    }

    public static Response favorImage(final String favor, ServletContext context) {
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

    public static Response giftsImage(final String gift, ServletContext context) {
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

    public static Response uploadUserImage(HttpServletRequest request, byte[] input, @HeaderParam("token") String token, ServletContext context) {
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

    public static Response uploadUserImage(HttpServletRequest request, byte[] input, int favorId, ServletContext context) {
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
