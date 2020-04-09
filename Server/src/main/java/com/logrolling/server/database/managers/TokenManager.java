package com.logrolling.server.database.managers;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Token;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TokenManager {

    public static void createToken(Token token) {

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO tokens (content, user) VALUES (?, ?);",
                new String[]{
                        token.getContent(),
                        token.getUser()
                });

        db.close();
    }

    public static void deleteToken(Token token) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from tokens where id = ?",
                new String[]{
                        Integer.toString(token.getId())

                });

        db.close();
    }

    public static Token getTokenFromUser(String user){

        Token token = null;
        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from tokens where user = ?",
                new String[]{
                        user
                });
        try {
            while (rs.next()) {
                token = getTokenFromResultSet(rs);
            }
        }  catch(SQLException e) {
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
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return tokens;
    }

    private static Token getTokenFromResultSet(ResultSet rs) throws SQLException{
        return new Token(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getString("user")
        );
    }

}
