package com.logrolling.server.chats;

import com.logrolling.server.authentication.AuthenticableController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/chats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatController extends AuthenticableController {

    // Does not take into account order of chats
    @GET
    public List<TransferChat> getChats(@HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        return ChatAssembler.getChats(username);
    }

    @GET
    @Path("/{otherUser}")
    public TransferChat getChat(@HeaderParam("token") String token, @PathParam("otherUser") String username2) {
        String username1 = authenticateWithToken(token);
        return ChatAssembler.getChat(username1, username2);
    }

    @POST
    public void addMessage(@HeaderParam("token") String token, TransferMessage transferMessage) {
        String username1 = authenticateWithToken(token);
        ChatAssembler.addMessage(username1, transferMessage.getTo(), transferMessage.getContent());
    }

}
