package com.logrolling.server.controllers;

import com.logrolling.server.transfer.LoginTransfer;
import com.logrolling.server.transfer.TransferToken;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tokens")
public class TokenController extends AuthenticableController {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferToken login(LoginTransfer loginTransfer) {
        return new TransferToken(authenticateWithPassword(loginTransfer.getUsername(), loginTransfer.getPassword()));
    }
}
