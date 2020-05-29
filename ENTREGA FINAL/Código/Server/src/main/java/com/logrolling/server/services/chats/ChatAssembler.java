package com.logrolling.server.services.chats;

import com.logrolling.server.integration.chats.MessageDAO;
import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.integration.users.UserDAO;
import com.logrolling.server.services.users.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChatAssembler {

    public static TransferChat getChat(String token, String user2) {
        String user1 = AuthenticationService.authenticateWithToken(token);
        List<Message> messages = MessageDAO.getMessagesFromConversation(user1, user2);
        List<TransferMessage> transfers = new ArrayList<TransferMessage>();
        for (Message m : messages)
            transfers.add(new TransferMessage(m));
        return new TransferChat(user1, user2, transfers);
    }

    public static List<TransferChat> getChats(String token) {
        //Be sure that user cannot see that there are no messages if they are not logged in
        AuthenticationService.authenticateWithToken(token);
        List<TransferChat> chats = new ArrayList<TransferChat>();
        for (User u : UserDAO.getAllUsers()) {
            TransferChat chat = getChat(token, u.getUsername());
            if (!chat.getMessages().isEmpty())
                chats.add(chat);
        }
        return chats;
    }

    public static List<TransferMessagePreview> getInteractions(String token) {
        String username = AuthenticationService.authenticateWithToken(token);
        List<TransferMessagePreview> interactions = new ArrayList<TransferMessagePreview>();
        List<Message> chatMessages = new ArrayList<Message>();
        List<Message> lastMessages = new ArrayList<Message>();

        for (User u : UserDAO.getAllUsers()) {

            //Skip self user
            if (u.getUsername().equals(username)) {
                continue;
            }

            chatMessages = MessageDAO.getMessagesFromConversation(username, u.getUsername());

            if (!chatMessages.isEmpty()) {
                lastMessages.add(chatMessages.get(chatMessages.size() - 1));
            }
        }

        lastMessages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return m2.getId() - m1.getId();
            }
        });

        for (Message m : lastMessages) {
            if (m.getFrom().equals(username))
                interactions.add(new TransferMessagePreview(m.getTo(), m.getContent()));
            else
                interactions.add(new TransferMessagePreview(m.getFrom(), m.getContent()));
        }

        return interactions;
    }

    public static void addMessage(String token, String username2, String content) {
        String username1 = AuthenticationService.authenticateWithToken(token);
        Message message = new Message(username1, username2, content);
        MessageDAO.createMessage(message);
    }

}
