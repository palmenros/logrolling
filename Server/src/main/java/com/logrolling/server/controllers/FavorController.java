package com.logrolling.server.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Filter;
import com.logrolling.server.model.Token;
import com.logrolling.server.transfer.TransferFavor;

import java.util.ArrayList;
import java.util.List;

@Path("/favor")
public class FavorController extends AuthenticableController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferFavor> getAvailableFavors(){
        List<Favor> favors = FavorManager.getAllFavors();
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @GET
    @Path("/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferFavor> getFavorsFromFilter(Filter filter) {
        List<Favor> favors = FavorManager.getFavorsByFilter(filter);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.TEXT_PLAIN)
    public List<TransferFavor> getFavorsFromUser(@HeaderParam("user") String username){
        List<Favor> favors = FavorManager.getFavorsFromUsername(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    // Change
    @GET
    @Path("/awarded")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferFavor> getAwardedFavors() {
        List<Favor> favors = FavorManager.getAwardedFavors();
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransferFavor addMessage(TransferFavor f){
        // FavorManager.createFavor(new Favor(f.getId(), f.getCreator(), f.getTitle(), f.getDescription(), f.getDueTime(), f.getReward(), f.getCoordinates().getLatitude(), f.getCoordinates().getLongitude()));
        return f;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public TransferFavor updateMessage(TransferFavor f, @PathParam("id") int id){
        // FavorManager.createFavor(new Favor(f.getId(), f.getCreator(), f.getTitle(), f.getDescription(), f.getDueTime(), f.getReward(), f.getCoordinates().getLatitude(), f.getCoordinates().getLongitude()));
        return f;
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteFavor(@PathParam("id") int id){
        // Favor f = FavorManager.getFavor(id);
        // FavorManager.deleteFavorFromCreatorAndTitle(f.getCreator(), f.getTitle());
    }

}
