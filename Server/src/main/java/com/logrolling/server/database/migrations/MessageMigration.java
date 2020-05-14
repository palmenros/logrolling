package com.logrolling.server.database.migrations;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.chats.MessageManager;
import com.logrolling.server.chats.Message;


public class MessageMigration implements Migration{
    @Override
    public void migrate() {
        String sqlQuery =
                "create table messages (" +
                        "id int auto_increment," +
                        "from varchar(50) not null," +
                        "to varchar(50) not null," +
                        "content varchar(150)," +
                        "constraint messages_pk primary key (id)" +
                        ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);
    }

    @Override
    public void fillDummy() {

        Message[] messageList = new Message[] {
                new Message("Pablo", "Pedro" , "Primer mensaje"),
                new Message("Pedro", "Pablo", "Recibido"),

        };

        for(Message message : messageList) {
            MessageManager.createMessage(message);
        }
    }
}
