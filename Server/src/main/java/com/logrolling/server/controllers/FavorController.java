package com.logrolling.server.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.exceptions.UnauthorizedException;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Filter;
import com.logrolling.server.model.Token;
import com.logrolling.server.transfer.TransferFavor;

import java.util.ArrayList;
import java.util.List;

@Path("/favors")
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferFavor> getFavorsFromUser(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        List<Favor> favors = FavorManager.getFavorsFromUsername(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    // TODO: Review from here and change
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
    public void addFavor(TransferFavor f, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        f.setCreator(username);
        FavorManager.createFavor(new Favor(f));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateFavor(TransferFavor f, @PathParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);

        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.updateFavor(id, new Favor(f));
        } else {
            throw new UnauthorizedException();
        }

    }
    
    @DELETE
    @Path("/{id}")
    public void deleteFavor(@PathParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);

        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.deleteFavorFromId(id);
        } else {
            throw new UnauthorizedException();
        }

    }

}
