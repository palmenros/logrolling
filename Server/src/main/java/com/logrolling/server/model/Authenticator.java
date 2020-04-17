package com.logrolling.server.model;

public class Authenticator {

    /**
     * Hashes a token for storage in database
     * @param token Token to be stored
     * @return Hashed token ready to be stored
     */
    public static String hashToken(String token)
    {
        //TODO: Implement
        return null;
    }

    /**
     * Hashes a password for storage in database
     * @param password
     * @return Hashed password ready to be stored
     */
    public static String hashPassword(String password) {
        //TODO: Implement
        return null;
    }

    /**
     * Compares a previously stored token with a given (unhashed) token
     * @param storedToken Previously hashed token
     * @param token Unhashed token to compare with storedToken
     * @return True if token coincides with storedToken
     */
    public static boolean matchToken(String storedToken, String token) {
        //TODO: Implement
        return false;
    }

     /**
     * Compares a previously stored password with a given (unhashed) password
     * @param storedPassword Previously hashed password
     * @param password Unhashed password to compare with storedPassword
     * @return True if password coincides with storedPassword
     */
    public static boolean matchPassword(String storedPassword, String password) {
        //TODO: Implement
        return false;
    }

}
