package com.logrolling.server.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.model.Favor;
import com.logrolling.server.transfer.TransferFavor;

import java.util.ArrayList;
import java.util.List;

@Path("/favor")
public class FavorResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferFavor> getFavors(){
        List<Favor> favors = FavorManager.getAllFavors();
        List<TransferFavor> transfers = new ArrayList<>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String returnTest(){
        return "test";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TransferFavor addMessage(TransferFavor f){
        FavorManager.createFavor(new Favor(f.getId(), f.getCreator(), f.getTitle(), f.getDescription(), f.getDueTime(), f.getReward(), f.getCoordinates().getLatitude(), f.getCoordinates().getLongitude()));
        return f;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deleteMessage(@PathParam("id") int id){
        // Favor f = FavorManager.getFavor(id);
        // FavorManager.deleteFavorFromCreatorAndTitle(f.getCreator(), f.getTitle());
    }

}
