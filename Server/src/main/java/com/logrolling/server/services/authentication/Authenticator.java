package com.logrolling.server.services.authentication;

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
     *
     * @param token Token or password to be stored
     * @return Hashed token or password ready to be stored
     */
    public static String hashToken(String token) {
        try {
            //Get salt and rawData
            byte[] salt = generateSalt();
            char[] rawData = token.toCharArray();

            //Create hashing specification with given data, salt, number of iterations and keyLength
            PBEKeySpec keySpecification = new PBEKeySpec(rawData, salt, numberOfIterations, keyLength);

            //Use secure PBKDF2 With Hmac SHA1 hashing algorithm
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            //Generated hash
            byte[] hash = secretKeyFactory.generateSecret(keySpecification).getEncoded();

            //Store format "{salt}:{hash}" both in hexadecimal
            return byteArrayToHexadecimal(salt) + ":" + byteArrayToHexadecimal(hash);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ServerErrorException(Response.serverError().entity(sw.toString()).build());
        }
    }


    /**
     * Compares a previously stored token or password with a given (unhashed) token or password
     *
     * @param storedToken Previously hashed token or password
     * @param token       Unhashed token or password to compare with storedToken
     * @return True if token coincides with storedToken
     */
    public static boolean matchToken(String storedToken, String token) {
        try {
            String[] rawParts = storedToken.split(":");
            byte[] storedSalt = hexadecimalToByteArray(rawParts[0]);
            byte[] storedHash = hexadecimalToByteArray(rawParts[1]);

            PBEKeySpec keySpecification = new PBEKeySpec(token.toCharArray(), storedSalt, numberOfIterations, keyLength);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] generatedHash = secretKeyFactory.generateSecret(keySpecification).getEncoded();

            //Differences will accumulate all differences between storedHash and generatedHash by xor-ing
            //If length isn't the same, both hashes aren't equal
            int differences = storedHash.length ^ generatedHash.length;

            //For each byte in hashes
            for (int i = 0; i < storedHash.length && i < generatedHash.length; i++) {
                //If both bytes aren't the same (XOR is 0), set differences to a value different than 0
                differences |= storedHash[i] ^ generatedHash[i];
            }

            //Tokens match only if 0 differences have been found
            return differences == 0;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ServerErrorException(Response.serverError().entity(sw.toString()).build());
        }
    }


    /**
     * Returns securely random generated token
     *
     * @return Random generated secure application token
     */
    public static String generateRandomToken() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] randomToken = new byte[tokenSize];
            secureRandom.nextBytes(randomToken);
            return byteArrayToHexadecimal(randomToken);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ServerErrorException(Response.serverError().entity(sw.toString()).build());
        }
    }


    /**
     * Generates salt to be used with hashing algorithm
     *
     * @return Securely generated salt
     * @throws NoSuchAlgorithmException Throws if secure algorithm is not available in device
     */
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[saltSize];
        secureRandom.nextBytes(salt);
        return salt;
    }


    /**
     * Convert array of bytes to a hexadecimal string
     *
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
        if (padding > 0) {
            //Prepend padding
            return String.format("%0" + padding + "d", 0) + hexString;
        } else {
            return hexString;
        }
    }

    /**
     * Converts and hexadecimal string to a byte array
     *
     * @param string String containing aligned to byte hexadecimal string
     * @return byte array
     */
    private static byte[] hexadecimalToByteArray(String string) {

        //string is aligned to byte boundary
        byte[] rawData = new byte[string.length() / 2];

        for (int i = 0; i < rawData.length; ++i) {
            //For each result byte, get corresponding two characters substring and convert to byte
            rawData[i] = (byte) Integer.parseInt(string.substring(2 * i, 2 * i + 2), 16);
        }
        return rawData;
    }

}
