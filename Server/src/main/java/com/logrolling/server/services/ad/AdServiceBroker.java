package com.logrolling.server.services.ad;

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
