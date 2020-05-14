package com.logrolling.server.services.chats;

import com.logrolling.server.services.users.UserManager;
import com.logrolling.server.services.users.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ChatAssembler {

    public static TransferChat getChat(String user1, String user2){
        List<Message> messages = MessageManager.getMessagesFromConversation(user1, user2);
        List<TransferMessage> transfers = new ArrayList<TransferMessage>();
        for(Message m : messages)
            transfers.add(new TransferMessage(m));
        return new TransferChat(user1, user2, transfers);
    }

    public static List<TransferChat> getChats(String username){
        List<TransferChat> chats = new ArrayList<TransferChat>();
        for(User u : UserManager.getAllUsers()){
            TransferChat chat = getChat(username, u.getUsername());
            if(!chat.getMessages().isEmpty())
                chats.add(chat);
        }
        return chats;
    }

    public static List<TransferMessagePreview> getInteractions(String username){
        List<TransferMessagePreview> interactions =  new ArrayList<TransferMessagePreview>();
        List<Message> chatMessages = new ArrayList<Message>();
        List<Message> lastMessages = new ArrayList<Message>();

        for(User u : UserManager.getAllUsers()){
            chatMessages = MessageManager.getMessagesFromConversation(username, u.getUsername());

            if(!chatMessages.isEmpty()) {
                lastMessages.add(chatMessages.get(chatMessages.size() - 1));
            }
        }

        lastMessages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return m2.getId() - m1.getId();
            }
        });

        for(Message m : chatMessages){
            if (m.getFrom().equals(username))
                interactions.add(new TransferMessagePreview(m.getFrom(), m.getContent()));
            else
                interactions.add(new TransferMessagePreview(m.getTo(), m.getContent()));
        }

        return interactions;
    }

    public static void addMessage(String username1, String username2, String content){
        Message message = new Message(username1, username2, content);
        MessageManager.createMessage(message);
    }

}
