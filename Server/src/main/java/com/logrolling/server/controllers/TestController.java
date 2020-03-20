package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.Favor;
import com.logrolling.server.transfer.TransferFavor;
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
    public List<TransferFavor> getMessage()  {
        List<TransferFavor> transferList = new ArrayList<TransferFavor>();
        Favor favor = new Favor("Pablo","Tirar la basura", "" , 10);
        FavorManager.createFavor(favor);
        for(Favor u : FavorManager.getAllFavors()) {
            transferList.add(new TransferFavor(u));
        }
        return transferList;
    }
}