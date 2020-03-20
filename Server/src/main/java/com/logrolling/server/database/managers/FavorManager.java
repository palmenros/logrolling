package com.logrolling.server.database.managers;


import com.logrolling.server.model.Favor;
import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FavorManager {

    public static void createFavor(Favor favor){

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO favors (creator, title, description, dueTime) VALUES (?, ?, ?, ?);",
                new String[]{
                        favor.getCreator(),
                        favor.getTitle(),
                        favor.getDescription(),
                        favor.getDueTime().toString()

                });

        db.close();

    }

    public static void deleteFavorFromCretorandTitle(String creator, String title){
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from favors where creator = ? and title = ?",
        new String[]{
           creator,
           title
        });

        db.close();


    }
    public static void deleteFavor(Favor favor) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from favors where id = ?",
                new String[]{
                        Integer.toString(favor.getId())

                });

        db.close();
    }


    public static List<Favor> getFavorsFromUsername(String username){

        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where creator = ?",
                new String[]{
                        username
                });

        try {
            while (rs.next()) {
                Favor favor = getFavorFromResultSet(rs);
                favors.add(favor);
            }
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return favors;

    }

    public static List<Favor> getAllFavors() {
        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors");

        try {
            while (rs.next()) {
                Favor favor = getFavorFromResultSet(rs);
                favors.add(favor);
            }
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return favors;
    }
    private static Favor getFavorFromResultSet(ResultSet rs) throws SQLException{
        return new Favor(
                rs.getInt("id"),
                rs.getString("creator"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("dueTime")
        );
    }
}
