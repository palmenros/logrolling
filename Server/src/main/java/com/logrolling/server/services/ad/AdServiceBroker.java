package com.logrolling.server.services.ad;


import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.services.users.User;
import com.logrolling.server.services.users.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//TODO: Refactor

@Path("ad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdServiceBroker {

    private static int adGrolliesReward = 10;

    @POST
    @Path("")
    public void addUserAdReward(@HeaderParam("token") String authToken) {
        String username = AuthenticationService.authenticateWithToken(authToken);
        User user = UserManager.getUserByName(username);

        UserManager.updateUserGrollies(username, user.getGrollies() + adGrolliesReward);
    }
}
