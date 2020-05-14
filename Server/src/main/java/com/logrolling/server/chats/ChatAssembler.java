package com.logrolling.server.chats;

import com.logrolling.server.users.TransferUser;
import com.logrolling.server.users.UserManager;
import com.logrolling.server.users.User;

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
        List<TransferChat> chats = getChats(username);
        List<TransferMessagePreview> interactions =  new ArrayList<TransferMessagePreview>();
        for(TransferChat c : chats){
            String u = c.getUser2();
            String m = getChat(username, c.getUser2()).getLastMessage();
            interactions.add(new TransferMessagePreview(u,m));
        }
        return interactions;
    }

    public static void addMessage(String username1, String username2, String content){
        Message message = new Message(username1, username2, content);
        MessageManager.createMessage(message);
    }

}
