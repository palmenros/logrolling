package com.logrolling.server.services.authentication;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tokens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TokenController extends AuthenticableController {

    @PUT
    public TransferToken login(LoginTransfer loginTransfer) {
        return new TransferToken(authenticateWithPassword(loginTransfer.getUsername(), loginTransfer.getPassword()));
    }
}
