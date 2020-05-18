package com.logrolling.server.services.gifts;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.services.users.User;
import com.logrolling.server.services.users.UserManager;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;

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

    public static void updateGift(Gift newGift) {

        Database db = DatabaseFactory.createInstance();

        int id = newGift.getId();

        db.executeUpdate("replace into gifts values(?, ?, ?, ?);",
                new String[]{
                        Integer.toString(id),
                        newGift.getTitle(),
                        newGift.getContent(),
                        newGift.getPrice().toString()
                });

        db.close();
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
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return gifts;
    }

    private static Gift getGiftFromResultSet(ResultSet rs) throws SQLException {
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
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (gift == null) {
            throw new DataNotFoundException("Gift not found");
        }
        return gift;
    }

    public static Gift getGiftById(Integer id) {
        Gift gift = null;

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts where id = ?",
                new String[]{
                        id.toString()
                });

        try {
            if (rs.next()) {
                gift = getGiftFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (gift == null) {
            throw new DataNotFoundException("Gift not found");
        }
        return gift;
    }

    public static List<Gift> getGiftsByPrice(int price) {
        List<Gift> gifts = new ArrayList<Gift>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts where price = ?",
                new String[]{
                        String.valueOf(price)
                });

        try {
            if (rs.next()) {
                while (rs.next()) {
                    Gift gift = getGiftFromResultSet(rs);
                    gifts.add(gift);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (gifts.size() == 0) {
            throw new DataNotFoundException("There are no gifts in database with price : " + price);
        }
        return gifts;
    }


    public static List<Gift> getGiftsByContent(String content) {
        List<Gift> gifts = new ArrayList<Gift>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts where content = ?",
                new String[]{
                        content
                });

        try {
            if (rs.next()) {
                while (rs.next()) {
                    Gift gift = getGiftFromResultSet(rs);
                    gifts.add(gift);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (gifts.size() == 0) {
            throw new DataNotFoundException("There are no gifts in database with content : " + content);
        }
        return gifts;
    }

    public static boolean alreadyAddedGift(String title) {
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
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        if (gift == null) {
            return false;
        } else return true;
    }

    public static void deleteGiftFromTitleAndContent(String title, String content) {
        Database db = DatabaseFactory.createInstance();
        db.executeUpdate("delete from gifts where title = ? and content = ?",
                new String[]{
                        title,
                        content
                });

        db.close();
    }


    public static void purchaseGift(String username, PurchasedGift gift) {

        User user = UserManager.getUserByName(username);
        Gift gift2 = getGiftById(gift.getGiftId());

        if (user.getGrollies() >= gift2.getPrice()) {
            Database db = DatabaseFactory.createInstance();
            db.executeUpdate(
                    "INSERT INTO purchasedGifts(idGift, address, user, sent) VALUES (?, ?, ?, ?);",
                    new String[]{
                            gift.getGiftId().toString(),
                            gift.getAddress(),
                            gift.getUsername(),
                            "0"
                    });

            db.close();
            UserManager.updateUserGrollies(username, user.getGrollies() - gift2.getPrice());
        } else
            throw new NotEnoughGrolliesException();
    }


    public static List<PurchasedGift> getPurchasedGifts() {
        List<PurchasedGift> gifts = new ArrayList<PurchasedGift>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from purchasedGifts");

        try {
            while (rs.next()) {
                PurchasedGift gift = getPurchasedGiftFromResultSet(rs);
                gifts.add(gift);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return gifts;
    }

    private static PurchasedGift getPurchasedGiftFromResultSet(ResultSet rs) throws SQLException {
        return new PurchasedGift(
                rs.getInt("id"),
                rs.getInt("giftId"),
                rs.getString("address"),
                rs.getString("username")
        );
    }

}
