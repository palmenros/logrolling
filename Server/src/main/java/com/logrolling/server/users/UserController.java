package com.logrolling.server.users;

import com.logrolling.server.authentication.AuthenticableController;

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
    public void createUser(@HeaderParam("username") String username, @HeaderParam("password") String password){
        User.createUser(username, password);
    }

    @PUT
    public void updateUserByName(@HeaderParam("token") String token, @HeaderParam("newUsername") String newU,
                                 @HeaderParam("newPassword") String password) {
        String old = authenticateWithToken(token);
        User.updateUserByName(old, newU, password);
    }

    @DELETE
    public void deleteUser(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        User.deleteUser(username);
    }

    @GET
    @Path("/user")
    public TransferUser getUserByName(@HeaderParam("username") String username){
        return User.getUserByName(username);
    }

}
