package com.logrolling.server.integration.gifts;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.services.gifts.Gift;
import com.logrolling.server.services.gifts.PurchasedGift;
import com.logrolling.server.services.users.User;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiftsDAO {

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

    public static List<Gift> getAllGifts() {
        List<Gift> gifts = new ArrayList<Gift>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from gifts order by price ASC");


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

    public static void purchaseGift(String username, PurchasedGift gift) {

        User user = UserDAO.getUserByName(username);
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
            UserDAO.updateUserGrollies(username, user.getGrollies() - gift2.getPrice());
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
