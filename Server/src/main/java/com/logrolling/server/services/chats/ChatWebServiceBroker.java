package com.logrolling.server.services.chats;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/chats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatWebServiceBroker {

    // Does not take into account order of chats
    @GET
    public List<TransferChat> getChats(@HeaderParam("token") String token) {
        return Chat.getChats(token);
    }

    @GET
    @Path("/{otherUser}")
    public TransferChat getChat(@HeaderParam("token") String token, @PathParam("otherUser") String username2) {
        return Chat.getChat(token, username2);
    }

    @GET
    @Path("/interactions")
    public List<TransferMessagePreview> getInteractions(@HeaderParam("token") String token) {
        return Chat.getInteractions(token);
    }

    @POST
    public void addMessage(@HeaderParam("token") String token, TransferMessage transferMessage) {
        Chat.addMessage(token, transferMessage.getTo(), transferMessage.getContent());
    }

}
