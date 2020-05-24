package com.logrolling.server.integration.users;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.services.authentication.Authenticator;
import com.logrolling.server.exceptions.AlreadyAddedException;
import com.logrolling.server.services.users.User;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    

    public static void createUser(User user) {


        if (getUserByName(user.getUsername()) == null) {
            Database db = DatabaseFactory.createInstance();
            db.executeUpdate(
                    "INSERT INTO users (username, password, grollies) VALUES (?, ?, ?);",
                    new String[]{
                            user.getUsername(),
                            Authenticator.hashToken(user.getPassword()),
                            user.getGrollies().toString()
                    });

            db.close();
        } else
            throw new AlreadyAddedException("El nombre de usuario ya está cogido");
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
            if (rs.next()) {
                user = getUserFromResultSet(rs);
                db.close();
                return user;
            } else
                db.close();
            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    public static void updateUserGrollies(String username, int newGrollies) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("update users set grollies=? where username = ?;",
                new String[]{
                        Integer.valueOf(newGrollies).toString(),
                        username
                });

        db.close();
    }

    public static void updateUserPassword(String username, String newPassword) {

        Database db = DatabaseFactory.createInstance();

        db.executeUpdate("update users set password=? where username = ?;",
                new String[]{
                        Authenticator.hashToken(newPassword),
                        username
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
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return users;
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("grollies")
        );
    }

}
