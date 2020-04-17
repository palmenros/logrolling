package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserController extends AuthenticableController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferUser> getMessage()  {
        List<TransferUser> transferList = new ArrayList<TransferUser>();

        for(User u : UserManager.getAllUsers()) {
            transferList.add(new TransferUser(u));
        }
        return transferList;
    }

    /*
    TODO: Uncomment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(String username, String password){
        UserManager.createUser(new User(username, password, 100));
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransferUser getUserByName(@PathParam("username") String username){
        return new TransferUser(UserManager.getUserByName(username));
    }
    */

    @Path("/auth/{user}/{password}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String passowordAuth(@PathParam("user") String user, @PathParam("password") String password) {
        return authenticateWithPassword(user, password);
    }

    @Path("/token/{token}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String tokenAuth(@PathParam("token") String token) {
        return authenticateWithToken(token);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserByName(TransferUser newUser){
        User user = new User(newUser.getId(), newUser.getUsername(), UserManager.getUserByName(newUser.getUsername()).getPassword(), newUser.getGrollies());
        UserManager.updateUserbyName(newUser.getUsername(), user);
    }

    @DELETE
    public void deleteUser(@HeaderParam("user") String username){
        UserManager.deleteUserByName(username);
    }

    @Path("/grollies")
    @GET
    public int getGrollies(@HeaderParam("user") String username){
        // return UserManager.getUserByName(username).getGrollies;
        return 0;
    }

}
