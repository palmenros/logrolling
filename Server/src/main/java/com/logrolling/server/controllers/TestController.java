package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/test")
public class TestController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferUser> getMessage()  {
        List<TransferUser> transferList = new ArrayList<TransferUser>();
        UserManager.deleteUserByName("Pablo");

        for(User u : UserManager.getAllUsers()) {
            transferList.add(new TransferUser(u));
        }



        return transferList;
    }


}