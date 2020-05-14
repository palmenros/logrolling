package com.logrolling.server.favors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.authentication.AuthenticableController;

import java.util.List;

@Path("/favors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavorController extends AuthenticableController {

    @GET
    public List<TransferFavor> getAvailableFavors(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        return Favor.getAvailableFavors(username);
    }

    @GET
    @Path("/filter")
    public List<TransferFavor> getFavorsFromFilter(@HeaderParam("token") String token, Filter filter) {
        String username = authenticateWithToken(token);
        return Favor.getFavorsFromFilter(username, filter);
    }

    @GET
    @Path("/user")
    public List<TransferFavor> getFavorsFromUser(@HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        return Favor.getFavorsFromUser(username);
    }

    @PUT
    @Path("@do")
    public void doFavor(@HeaderParam("id") int id, @HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        Favor.doFavor(id, username);
    }

    @PUT
    @Path("@complete")
    public void completeFavor(@HeaderParam("id") int id, @HeaderParam("token") String token){
        String username = authenticateWithToken(token);
        Favor.completeFavor(id, username);
    }

    @GET
    @Path("/awarded")
    public List<TransferFavor> getAwardedFavors(@HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        return Favor.getAwardedFavors(username);
    }

    @POST
    public void addFavor(TransferFavor f, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        Favor.addFavor(f, username);
    }

    @PUT
    public void updateFavor(TransferFavor f, @HeaderParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        Favor.updateFavor(f, id, username);
    }
    
    @DELETE
    public void deleteFavor(@HeaderParam("id") int id, @HeaderParam("token") String token) {
        String username = authenticateWithToken(token);
        Favor.deleteFavor(id, username);
    }

}
