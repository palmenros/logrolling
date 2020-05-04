package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.MessageManager;
import com.logrolling.server.database.managers.UserManager;
import com.logrolling.server.exceptions.AuthenticationException;
import com.logrolling.server.exceptions.UnauthorizedException;
import com.logrolling.server.model.Message;
import com.logrolling.server.model.User;
import com.logrolling.server.transfer.ChatAssembler;
import com.logrolling.server.transfer.TransferChat;
import com.logrolling.server.transfer.TransferMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/chats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatController extends AuthenticableController {

    // Does not take into account order of chats
    @GET
    public List<TransferChat> getChats(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        List<TransferChat> chats = new ArrayList<TransferChat>();
        for(User u : UserManager.getAllUsers()){
            TransferChat chat = ChatAssembler.getChat(username, u.getUsername());
            if(!chat.getMessages().isEmpty())
                chats.add(chat);
        }
        return chats;
    }

    @GET
    public TransferChat getChat(@HeaderParam("token") String token, String username2){
        String username1 = authenticateWithToken(token);
        return ChatAssembler.getChat(username1, username2);
    }

    @POST
    public void addMessage(@HeaderParam("token") String token, TransferMessage message){
        String username = authenticateWithToken(token);
        if(message.getFrom().equals(username)) {
            // Message was created by current user
            MessageManager.createMessage(new Message(message));
        }
        else{
            throw new UnauthorizedException();
        }
    }

}
