package com.logrolling.server.database.managers;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;
import com.mysql.cj.protocol.Resultset;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    //TODO: Finish implementing

    public static void createUser(User user) {

        //TODO: Add all user attributes

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
           "INSERT INTO users (username, password, grollies) VALUES (?, ?, ?);",
        new String[]{
            user.getUsername(),
            user.getPassword(),
            user.getGrollies().toString()
        });

        db.close();
    }

    public static User getUserByName(String username) {

        User user;
        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery(
                "select * from users where Username = ?;",
                new String[]{
                        username
                }
        );
        try {
           if(rs.next()){
               user = getUserFromResultSet(rs);
               db.close();
               return user;
           }
           else
               db.close();
               return null;
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }

    }

    public static void updateUserbyName(String username, User newUser) {
        Database db = DatabaseFactory.createInstance();
        User user = getUserByName(username);

        int id = user.getId();

        db.executeUpdate("replace into users values(?, ?, ?, ?);",
                new String[]{

                        Integer.toString(id),
                        newUser.getUsername(),
                        newUser.getPassword(),
                        newUser.getGrollies().toString()
                });

        db.close();
    }

    public static void deleteUserByName(String username) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from users where username = ?",
                new String[]{
                        username,

                });

        db.close();



    }

    @NotNull
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from users");

        try {
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return users;
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException{
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("grollies")
        );
    }

}
