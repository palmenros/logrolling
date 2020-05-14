package com.logrolling.server.services.users;

import com.logrolling.server.services.authentication.AuthenticableController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController extends AuthenticableController {

    @GET
    public List<TransferUser> getUsers()  {
        return User.getUsers();
    }

    @POST
    public void createUser(TransferCredentials transferCredentials){
        User.createUser(transferCredentials.getUsername(), transferCredentials.getPassword());
    }

    @PUT
    public void updateUserByName(@HeaderParam("token") String token, TransferCredentials transferCredentials) {
        String old = authenticateWithToken(token);
        User.updateUserByName(old, transferCredentials.getUsername(), transferCredentials.getPassword());
    }

    @DELETE
    public void deleteUser(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        User.deleteUser(username);
    }

    @GET
    @Path("/user/{username}")
    public TransferUser getUserByName(@PathParam("username") String username){
        return User.getUserByName(username);
    }

}
