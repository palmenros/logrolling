package com.logrolling.server.services.ad;

import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.services.users.User;
import com.logrolling.server.services.users.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("ad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdServiceBroker {

    @POST
    @Path("")
    public void addUserAdReward(@HeaderParam("token") String authToken) {
        Ad.addUserAdReward(authToken);
    }
}
