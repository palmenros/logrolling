package com.logrolling.server.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.database.managers.FavorManager;
import com.logrolling.server.database.migrations.FavorMigration;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;
import com.logrolling.server.exceptions.UnauthorizedException;
import com.logrolling.server.model.Favor;
import com.logrolling.server.model.Filter;
import com.logrolling.server.model.Token;
import com.logrolling.server.transfer.TransferFavor;

import java.util.ArrayList;
import java.util.List;

@Path("/favors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavorController extends AuthenticableController {

    @GET
    public List<TransferFavor> getAvailableFavors(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        List<Favor> favors = FavorManager.getAvailableFavors(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @GET
    @Path("/filter")
    public List<TransferFavor> getFavorsFromFilter(@HeaderParam("token") String token, Filter filter) {
        String username = authenticateWithToken(token);
        List<Favor> favors = FavorManager.getFavorsByFilter(username, filter);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @GET
    @Path("/user")
    public List<TransferFavor> getFavorsFromUser(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        List<Favor> favors = FavorManager.getFavorsFromUsername(username);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @PUT
    @Path("@do")
    public void doFavor(@HeaderParam("id") int id, @HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        FavorManager.doFavor(id, username);
    }

    @PUT
    @Path("@complete")
    public void completeFavor(@HeaderParam("id") int id, @HeaderParam("token") String token){
        String username = authenticateWithToken(token);

        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.completeFavor(id);
        } else {
            throw new UnauthorizedException();
        }
    }

    @GET
    @Path("/awarded")
    public List<TransferFavor> getAwardedFavors(@HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        List<TransferFavor> transfers = new ArrayList<TransferFavor>();
        List<Favor> favors = FavorManager.getAwardedFavors(username);
        for (Favor f : favors) {
            transfers.add(new TransferFavor(f));
        }
        return transfers;
    }

    @POST
    public void addFavor(TransferFavor f, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        if(f.getCreator().equals(username)) {
            //Favor was created by current user
            try {
                FavorManager.createFavor(new Favor(f));
            }
            catch(NotEnoughGrolliesException e){
                throw new NotEnoughGrolliesException();
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    @PUT
    public void updateFavor(TransferFavor f, @HeaderParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);

        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.updateFavor(id, new Favor(f));
        } else {
            throw new UnauthorizedException();
        }
    }
    
    @DELETE
    public void deleteFavor(@HeaderParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);

        if(FavorManager.getFavorById(id).getCreator().equals(username)) {
            //Favor was created by current user
            FavorManager.deleteFavorFromId(id);
        } else {
            throw new UnauthorizedException();
        }
    }

}
