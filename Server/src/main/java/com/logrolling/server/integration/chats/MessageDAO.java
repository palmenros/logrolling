package com.logrolling.server.integration.chats;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.DatabaseException;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.services.chats.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageDAO {

    public static void createMessage(Message message) {//add message

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(
                "INSERT INTO messages (origin, destination, content) VALUES (?, ?, ?);",
                new String[]{
                        message.getFrom(),
                        message.getTo(),
                        message.getContent()

                });

        db.close();

    }

    public static List<Message> getMessagesFromConversation(String userA, String userB) {

        List<Message> messages = new ArrayList<Message>();

        Database db = DatabaseFactory.createInstance();
        ResultSet rs = db.executeQuery("select * from messages where origin = ? and destination = ? or origin = ? and destination = ? order by id asc",
                new String[]{
                        userA,
                        userB,
                        userB,
                        userA
                });

        try {
            while (rs.next()) {
                Message message = getMessageFromResultSet(rs);
                messages.add(message);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        db.close();

        return messages;
    }

    private static Message getMessageFromResultSet(ResultSet rs) throws SQLException {
        return new Message(
                rs.getInt("id"),
                rs.getString("origin"),
                rs.getString("destination"),
                rs.getString("content")

        );
    }
}
