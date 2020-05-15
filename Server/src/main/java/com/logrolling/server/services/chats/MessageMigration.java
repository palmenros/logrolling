package com.logrolling.server.services.chats;

import com.logrolling.server.database.Database;
import com.logrolling.server.database.factories.DatabaseFactory;
import com.logrolling.server.database.migrations.Migration;
import com.logrolling.server.services.chats.MessageManager;
import com.logrolling.server.services.chats.Message;


public class MessageMigration implements Migration {
    @Override
    public void migrate() {
        String sqlQuery =
                "create table messages (" +
                        "id int auto_increment," +
                        "origin varchar(50) not null," +
                        "destination varchar(50) not null," +
                        "content varchar(150)," +
                        "constraint messages_pk primary key (id)" +
                        ");";

        Database db = DatabaseFactory.createInstance();
        db.executeUpdate(sqlQuery);
    }

    @Override
    public void fillDummy() {

        Message[] messageList = new Message[] {
                new Message("pablo", "pedro" , "Primer mensaje"),
                new Message("pedro", "pablo", "Recibido"),
                new Message("pablo", "pedro", "ok"),
                new Message("juancarlos", "pedro", "Soy Juan Carlos")
        };

        for(Message message : messageList) {
            MessageManager.createMessage(message);
        }
    }
}
