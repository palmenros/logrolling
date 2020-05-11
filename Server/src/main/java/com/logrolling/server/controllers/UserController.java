package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController extends AuthenticableController {

    static final int INITIAL_GROLLIES = 100;

    @GET
    public List<TransferUser> getUsers()  {
        List<TransferUser> transferList = new ArrayList<TransferUser>();

        for(User u : UserManager.getAllUsers()) {
            transferList.add(new TransferUser(u));
        }
        return transferList;
    }

    @POST
    public void createUser(@HeaderParam("username") String username, @HeaderParam("password") String password){
        UserManager.createUser(new User(username, password, INITIAL_GROLLIES));
    }

    @PUT
    public void updateUserByName(TransferUser newUser){
        User user = new User(newUser.getId(), newUser.getUsername(), UserManager.getUserByName(newUser.getUsername()).getPassword(), newUser.getGrollies());
        UserManager.updateUserbyName(newUser.getUsername(), user);
    }

    @DELETE
    public void deleteUser(@HeaderParam("user") String username){
        UserManager.deleteUserByName(username);
    }

    @GET
    @Path("/user")
    public TransferUser getUserByName(@HeaderParam("username") String username){
        return new TransferUser(UserManager.getUserByName(username));
    }

    @GET
    @Path("/grollies")
    public int getGrollies(@HeaderParam("user") String username){
         return UserManager.getUserByName(username).getGrollies();
    }
}
