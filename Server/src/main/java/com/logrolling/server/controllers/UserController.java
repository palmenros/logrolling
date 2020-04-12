package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.Token;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(String username, String password){
        UserManager.createUser(new User(username, password));
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransferUser getUserByName(@PathParam("username") String username){
        return new TransferUser(UserManager.getUserByName(username));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserByName(TransferUser newUser){
        User user = new User(newUser.getId(), newUser.getUsername(), UserManager.getUserByName(newUser.getUsername()).getPassword());
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
