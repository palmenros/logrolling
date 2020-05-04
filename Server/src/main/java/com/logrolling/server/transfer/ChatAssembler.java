package com.logrolling.server.transfer;

import com.logrolling.server.database.managers.MessageManager;
import com.logrolling.server.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatAssembler {

    public static TransferChat getChat(String user1, String user2){
        List<Message> messages = MessageManager.getMessagesFromConversation(user1, user2);
        List<TransferMessage> transfers = new ArrayList<TransferMessage>();
        for(Message m : messages)
            transfers.add(new TransferMessage(m));
        return new TransferChat(user1, user2, transfers);
    }

}
