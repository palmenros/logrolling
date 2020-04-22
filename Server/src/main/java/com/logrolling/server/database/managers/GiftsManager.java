package com.logrolling.server.database.managers;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.model.Filter;
import com.logrolling.server.model.Gift;
import com.logrolling.server.model.Token;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiftsManager {

    public static void createGift(Gift gift) {

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO gifts (title, content, price) VALUES (?, ?, ?);",
                new String[]{
                        gift.getTitle(),
                        gift.getContent(),
                        gift.getPrice().toString()
                });

        db.close();
    }

    public static void deleteGiftFromId(Integer id){
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from gifts where id = ?",
                new String[]{
                        id.toString()
                });

        db.close();
    }

    public static void deleteGiftFromTitleAndContent(String title, String content){
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from gifts where title = ? and content = ?",
                new String[]{
                        title,
                        content
                });

        db.close();
    }

    public static void updateGift(int id, Gift newGift){
        //TODO: Implement this
    }

    public static void deleteGift(Gift gift) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from gifts where id = ?",
                new String[]{
                        Integer.toString(gift.getId())

                });

        db.close();
    }


    public static List<Gift> getAllGifts() {
        List<Gift> gifts = new ArrayList<Gift>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts");

        try {
            while (rs.next()) {
                Gift gift = getGiftFromResultSet(rs);
                gifts.add(gift);
            }
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return gifts;
    }
    private static Gift getGiftFromResultSet(ResultSet rs) throws SQLException{
        return new Gift(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getInt("price")
        );
    }


    public static Gift getGiftByTitle(String title) {
        Gift gift = null;

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts where title = ?",
                new String[]{
                        title
                });

        try {
            if (rs.next()) {
                gift = getGiftFromResultSet(rs);
            }
        }  catch(SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if(gift == null) {
            throw new DataNotFoundException("Gift not found");
        }
        return gift;
    }
}
