package com.logrolling.server.services.users;

import com.logrolling.server.services.authentication.AuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWebServiceBroker {

    @GET
    public TransferUser getLoggedUser(@HeaderParam("token") String token) {
        return User.getLoggedUser(token);
    }

    @POST
    public void createUser(TransferCredentials transferCredentials) {
        User.createUser(transferCredentials.getUsername(), transferCredentials.getPassword());
    }

    @PUT
    public void updateUserByName(@HeaderParam("token") String token, TransferCredentials transferCredentials) {
        User.updateUserByName(token, transferCredentials.getPassword());
    }

    @DELETE
    public void deleteUser(@HeaderParam("token") String token) {
        User.deleteUser(token);
    }

    @GET
    @Path("/{username}")
    public TransferUser getUserByName(@PathParam("username") String username) {
        return User.getUserByName(username);
    }

}
