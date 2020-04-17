package com.logrolling.server.controllers;


import com.logrolling.server.database.managers.TokenManager;
import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.exceptions.AuthenticationException;
import com.logrolling.server.model.Authenticator;
import com.logrolling.server.model.User;

public class AuthenticableController {

    //TODO: Add proper documentation and exceptions

    //If authentication is not successful, throw an exception
    //If authentication is successful, return username of authenticated user
    protected String authenticateWithToken(String token) throws AuthenticationException {
        try {
            String[] tokenParts = token.split(":", 2);
            int tokenId = Integer.parseInt(tokenParts[0]);


            return Integer.toString(tokenId);

        } catch (Exception e) {
            throw new AuthenticationException();
        }

    }


    /**
     * Authenticate an user with username and password
     * If authentication is not successful, throw an exception
     * Else generate a new token and return it
     * @param username Username
     * @param password Password
     * @return Newly generated token to identify this user
     * @throws AuthenticationException If authentication is not successful
     */
    protected String authenticateWithPassword(String username, String password) throws AuthenticationException {
        try {
            User user = UserManager.getUserByName(username);

            if (user == null) {
                //User not found
                throw new AuthenticationException();
            }

            //User is found
            if(Authenticator.matchToken(user.getPassword(), password)) {
                //Correct password, generate a new token for user
                return TokenManager.createToken(username);
            } else {
                //Incorrect password
                throw new AuthenticationException();
            }

        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }

}
