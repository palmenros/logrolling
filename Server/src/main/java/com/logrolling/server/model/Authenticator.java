package com.logrolling.server.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Authenticator {

    //Number of iterations to use on algorithm, higher is slower and more secure
    private static final int numberOfIterations = 1000;

    //Size in bytes of the length of generated hash
    private static final int saltSize = 16;

    //Size in bits of the length of generated hash
    private static final int keyLength = 512;

    //Size in bytes of application token
    private static final int tokenSize = 64;

    /**
     * Hashes a token or password for storage in database
     * @param token Token or password to be stored
     * @return Hashed token or password ready to be stored
     */
    public static String hashToken(String token)
    {
        try {
            //Get salt and rawData
            byte[] salt = generateSalt();
            char[] rawData = token.toCharArray();

            //Create hashing specification with given data, salt, number of iterations and keyLength
            PBEKeySpec keySpecification = new PBEKeySpec(rawData, salt, numberOfIterations, keyLength);

            //Use secure PBKDF2 With Hmac SHA1 hashing algorithm
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            //Generated hash
            byte[] hash = skf.generateSecret(keySpecification).getEncoded();

            //Store format "{salt}:{hash}" both in hexadecimal
            return byteArrayToHexadecimal(salt) + ":" + byteArrayToHexadecimal(hash);

        } catch(Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ServerErrorException( Response.serverError().entity(sw.toString()).build());
        }
    }

    /**
     * Compares a previously stored token or password with a given (unhashed) token or password
     * @param storedToken Previously hashed token or password
     * @param token Unhashed token or password to compare with storedToken
     * @return True if token coincides with storedToken
     */
    public static boolean matchToken(String storedToken, String token) {
        //TODO: Implement
        return false;
    }

    /**
     * Returns securely random generated token
     * @return Random generated secure application token
     */
    public static String generateRandomToken() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] randomToken = new byte[tokenSize];
            secureRandom.nextBytes(randomToken);
            return byteArrayToHexadecimal(randomToken);
        } catch(Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ServerErrorException( Response.serverError().entity(sw.toString()).build());
        }
    }


    /**
     * Generates salt to be used with hashing algorithm
     * @return  Securely generated salt
     * @throws NoSuchAlgorithmException
     */
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[saltSize];
        secureRandom.nextBytes(salt);
        return salt;
    }


    /**
     * Convert array of bytes to a hexadecimal string
     * @param rawData byte array containing all raw data
     * @return String representing the byte array as a string
     */
    private static String byteArrayToHexadecimal(byte[] rawData) {

        //rawData representation as a positive number (signum 1)
        BigInteger number = new BigInteger(1, rawData);

        //Return hexadecimal representation of number in base 16 (hexadecimal)
        String hexString = number.toString(16);

        //Calculate necessary padding for alignment
        int padding = (rawData.length * 2) - hexString.length();
        if(padding > 0) {
            //Prepend padding
            return String.format("%0" + padding + "d", 0) + hexString;
        }else{
            return hexString;
        }
    }

}
