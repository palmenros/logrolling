package com.logrolling.server.integration.authentication;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.services.authentication.Authenticator;
import com.logrolling.server.services.authentication.Token;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO {

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


    private static Token getTokenFromResultSet(ResultSet rs) throws SQLException {
        return new Token(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getString("user")
        );
    }

}
