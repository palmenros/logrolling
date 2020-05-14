package com.logrolling.server.services.favors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.logrolling.server.services.authentication.AuthenticationService;

import java.util.List;

@Path("/favors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavorController {

    @GET
    public List<TransferFavor> getAvailableFavors(@HeaderParam("token") String token){
        return Favor.getAvailableFavors(token);
    }

    @GET
    @Path("/filter")
    public List<TransferFavor> getFavorsFromFilter(@HeaderParam("token") String token, Filter filter) {
        return Favor.getFavorsFromFilter(token, filter);
    }

    @GET
    @Path("/user")
    public List<TransferFavor> getFavorsFromUser(@HeaderParam("token") String token){
        return Favor.getFavorsFromUser(token);
    }

    @PUT
    @Path("/{id}/@do")
    public void doFavor(@HeaderParam("token") String token, @PathParam("id") int id){
        Favor.doFavor(id, token);
    }

    @PUT
    @Path("/{id}/@complete")
    public void completeFavor(@HeaderParam("token") String token, @PathParam("id") int id){
        Favor.completeFavor(id, token);
    }

    @GET
    @Path("/awarded")
    public List<TransferFavor> getAwardedFavors(@HeaderParam("token") String token) {
        return Favor.getAwardedFavors(token);
    }

    @POST
    public void addFavor(@HeaderParam("token") String token, TransferFavor f) {
        Favor.addFavor(f, token);
    }

    @PUT
    @Path("/{id}")
    public void updateFavor(@HeaderParam("token") String token, @PathParam("id") int id, TransferFavor f) {
        Favor.updateFavor(f, id, token);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteFavor(@HeaderParam("token") String token, @PathParam("id") int id) {
        Favor.deleteFavor(id, token);
    }

}