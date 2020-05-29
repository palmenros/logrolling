package com.logrolling.server.services.authentication;


import com.logrolling.server.integration.authentication.TokenDAO;
import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.exceptions.AuthenticationException;
import com.logrolling.server.services.users.User;

public class AuthenticationService {

    //If authentication is not successful, throw an exception
    //If authentication is successful, return username of authenticated user
    public static String authenticateWithToken(String tokenString) throws AuthenticationException {
        try {
            String[] tokenParts = tokenString.split(":", 2);
            int tokenId = Integer.parseInt(tokenParts[0]);

            Token token = TokenDAO.getToken(tokenId);
            if (token == null) {
                //Invalid token number
                throw new AuthenticationException();
            }

            if (Authenticator.matchToken(token.getContent(), tokenParts[1])) {
                //Successfully authorized user, return username
                return token.getUser();
            } else {
                //Incorrect token
                throw new AuthenticationException();
            }

        } catch (Exception e) {
            throw new AuthenticationException();
        }

    }

    /**
     * Authenticate an user with username and password
     * If authentication is not successful, throw an exception
     * Else generate a new token and return it
     *
     * @param username Username
     * @param password Password
     * @return Newly generated token to identify this user
     * @throws AuthenticationException If authentication is not successful
     */
    public static String authenticateWithPassword(String username, String password) throws AuthenticationException {
        try {
            User user = UserDAO.getUserByName(username);

            if (user == null) {
                //User not found
                throw new AuthenticationException();
            }

            //User is found
            if (Authenticator.matchToken(user.getPassword(), password)) {
                //Correct password, generate a new token for user
                return TokenDAO.createToken(username);
            } else {
                //Incorrect password
                throw new AuthenticationException();
            }

        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }

}
