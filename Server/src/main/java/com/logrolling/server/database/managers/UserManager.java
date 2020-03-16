package com.logrolling.server.database.managers;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.TransferUser;

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
           "INSERT INTO users (username, password) VALUES (?, ?);",
        new String[]{
            user.getUsername(),
            user.getPassword()
        });

        db.close();
    }

    public static User getUserById(int id) {
        //TODO: Implement
        return null;
    }

    public static void updateUser(int id, User newUser) {
         //TODO: Implement
    }

    public static void deleteUser(int id) {
        //TODO: Implement
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

        return users;
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException{
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password")
        );
    }

}
