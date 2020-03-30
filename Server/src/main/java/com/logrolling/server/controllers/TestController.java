package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.database.managers.TokenManager;
import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Filter;
import com.logrolling.server.model.Token;
import com.logrolling.server.transfer.TransferFavor;
import com.logrolling.server.transfer.TransferToken;
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
    public List<TransferToken> getMessage()  {
        List<TransferToken> transferList = new ArrayList<TransferToken>();

        for(Token u : TokenManager.getAllTokens()) {
            transferList.add(new TransferToken(u));
        }
        return transferList;
    }
}