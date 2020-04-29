package com.logrolling.server.controllers;

import com.logrolling.server.model.Chat;
import com.logrolling.server.transfer.TransferChat;
import com.logrolling.server.transfer.TransferMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/chats")
public class ChatController extends AuthenticableController {

    // TO DO

    // Change, make it implementing TOA
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferChat> getChats(){
        return new ArrayList<TransferChat>();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferChat createChat(Chat chat){
        return new TransferChat();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferChat> getChat(@PathParam("username") String username){
        // ChatManager.getChatByUsername(username);
        return new ArrayList<TransferChat>();
    }

    @POST
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferChat addMessage(@PathParam("username") String username, TransferMessage message){
        return new TransferChat();
    }

    @DELETE
    @Path("/{username}")
    public void deleteChat(@PathParam("username") String username){

    }

}
