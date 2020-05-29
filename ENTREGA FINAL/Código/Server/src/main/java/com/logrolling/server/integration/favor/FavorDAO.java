package com.logrolling.server.integration.favor;


import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.services.favors.Favor;
import com.logrolling.server.services.favors.Filter;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;
import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.services.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FavorDAO {

    public static void createFavorUnchecked(Favor favor) {
        insertFavorToDatabase(favor);
    }

    public static void createFavor(Favor favor) {

        String username = favor.getCreator();
        User user = UserDAO.getUserByName(username);

        if (user.getGrollies() >= favor.getReward()) {
            insertFavorToDatabase(favor);
            UserDAO.updateUserGrollies(username, user.getGrollies() - favor.getReward());
        } else
            throw new NotEnoughGrolliesException();

    }

    private static void insertFavorToDatabase(Favor favor) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO favors (creator, title, description, dueTime, reward, latitude, longitude, worker, completed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                new String[]{
                        favor.getCreator(),
                        favor.getTitle(),
                        favor.getDescription(),
                        favor.getDueTime().toString(),
                        favor.getReward().toString(),
                        favor.getLatCoord().toString(),
                        favor.getLongCoord().toString(),
                        favor.getWorker(),
                        Integer.valueOf(favor.getCompleted() ? 1 : 0).toString()
                });

        db.close();
    }

    public static void deleteFavorFromId(Integer id) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from favors where id = ?",
                new String[]{
                        id.toString()
                });

        db.close();


    }


    public static void updateFavor(int id, Favor newFavor) {
        Database db = DatabaseFactory.createInstance();
        Favor favor = getFavorById(id);
        db.executeUpdate("replace into favors values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                new String[]{

                        Integer.toString(id),
                        newFavor.getCreator(),
                        newFavor.getTitle(),
                        newFavor.getDescription(),
                        newFavor.getDueTime().toString(),
                        newFavor.getReward().toString(),
                        newFavor.getLatCoord().toString(),
                        newFavor.getLongCoord().toString(),
                        newFavor.getWorker(),
                        Integer.valueOf(newFavor.getCompleted() ? 1 : 0).toString()
                });

        db.close();

    }


    public static List<Favor> getFavorsFromUsername(String username) {

        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where completed = 0 and creator = ? ",
                new String[]{
                        username
                });

        try {
            while (rs.next()) {
                Favor favor = getFavorFromResultSet(rs);
                favors.add(favor);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return favors;

    }


    private static Favor getFavorFromResultSet(ResultSet rs) throws SQLException {
        return new Favor(
                rs.getInt("id"),
                rs.getString("creator"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("dueTime"),
                rs.getInt("reward"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getString("worker"),
                rs.getInt("completed") == 1
        );
    }

    public static List<Favor> getFavorsByFilter(String username, Filter filter) {
        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        if (filter.getMaxDistance() == -1) {
            ResultSet rs = db.executeQuery("select * from favors where worker is null and dueTime >= ? and reward >= ? and creator <> ?",
                    new String[]{
                            filter.getMinDate().toString(),
                            filter.getMinGrollies().toString(),
                            username
                    });

            try {
                while (rs.next()) {
                    Favor favor = getFavorFromResultSet(rs);
                    favors.add(favor);
                }
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        } else {
            ResultSet rs = db.executeQuery("select * from favors where creator <> ? and worker is null and dueTime >= ? and reward >= ? and (111.111*DEGREES(ACOS(LEAST(1.0, COS(RADIANS(latitude))*COS(RADIANS(?))*COS(RADIANS(longitude-?)) + SIN(RADIANS(latitude))*SIN(RADIANS(?)))))) < ?",
                    new String[]{
                            username,
                            filter.getMinDate().toString(),
                            filter.getMinGrollies().toString(),
                            filter.getCoordinates().getLatitude().toString(),
                            filter.getCoordinates().getLongitude().toString(),
                            filter.getCoordinates().getLatitude().toString(),
                            filter.getMaxDistance().toString()
                    });

            try {
                while (rs.next()) {
                    Favor favor = getFavorFromResultSet(rs);
                    favors.add(favor);
                }
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }


        db.close();

        return favors;
    }

    public static List<Favor> getAwardedFavors(String username) {

        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where worker = ? and completed = 0",
                new String[]{
                        username
                });

        try {
            while (rs.next()) {
                Favor favor = getFavorFromResultSet(rs);
                favors.add(favor);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return favors;
    }

    public static List<Favor> getAvailableFavors(String username) {

        List<Favor> favors = new ArrayList<Favor>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where creator <> ? and worker is null",
                new String[]{
                        username
                });

        try {
            while (rs.next()) {
                Favor favor = getFavorFromResultSet(rs);
                favors.add(favor);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return favors;
    }

    public static Favor getFavorById(int id) {
        Favor favor = null;

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where id = ?",
                new String[]{
                        Integer.toString(id)
                });

        try {
            if (rs.next()) {
                favor = getFavorFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (favor == null) {
            throw new DataNotFoundException("Favor not found");
        }
        return favor;
    }

    public static Favor getLatestFavorFromUser(String username) {
        Favor favor = null;

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from favors where creator = ? order by id desc",
                new String[]{
                        username
                });

        try {
            if (rs.next()) {
                favor = getFavorFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (favor == null) {
            throw new DataNotFoundException("Favor not found");
        }
        return favor;
    }
}
