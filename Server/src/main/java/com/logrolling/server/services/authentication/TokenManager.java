package com.logrolling.server.services.authentication;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TokenManager {

    public static String createToken(String username) {
        return createToken(new Token(Authenticator.generateRandomToken(), username));
    }

    public static String createToken(Token token) {
        Database db = DatabaseFactory.createInstance();

        String hashedContent = Authenticator.hashToken(token.getContent());

        db.executeUpdate(
                "INSERT INTO tokens (content, user) VALUES (?, ?);",
                new String[]{
                        hashedContent,
                        token.getUser()
                });

        //Get newly inserted id
        ResultSet rs = db.executeQuery("select id from tokens where content = ?",
                new String[]{
                        hashedContent
                });

        int id;

        try {
            if (rs.next()) {
                id = rs.getInt("id");
            } else {
                throw new DatabaseException("Cannot get newly generated Id from database");
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }

        db.close();

        return id + ":" + token.getContent();
    }

    public static void deleteToken(Token token) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from tokens where id = ?",
                new String[]{
                        Integer.toString(token.getId())

                });

        db.close();
    }

    public static void deleteAllTokensFromUser(String username) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from tokens where user = ?",
                new String[]{
                        username
                });

        db.close();
    }

    public static Token getToken(int id) {

        Token token = null;
        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from tokens where id = ?",
                new String[]{
                        Integer.toString(id)
                });
        try {
            while (rs.next()) {
                token = getTokenFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return token;


    }

    public static List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<Token>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from tokens");

        try {
            while (rs.next()) {
                Token token = getTokenFromResultSet(rs);
                tokens.add(token);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return tokens;
    }

    private static Token getTokenFromResultSet(ResultSet rs) throws SQLException {
        return new Token(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getString("user")
        );
    }

}
