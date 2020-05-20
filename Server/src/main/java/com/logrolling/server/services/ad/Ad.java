package com.logrolling.server.services.ad;

import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.services.users.User;
import com.logrolling.server.services.users.UserManager;

public class Ad {

    private static final int adGrolliesReward = 10;

    public static void addUserAdReward(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        User user = UserManager.getUserByName(username);
        UserManager.updateUserGrollies(username, user.getGrollies() + adGrolliesReward);
    }

}
