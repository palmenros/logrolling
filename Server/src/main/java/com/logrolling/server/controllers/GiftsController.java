package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.exceptions.AlreadyAddedException;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.model.Gift;
import com.logrolling.server.transfer.TransferGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsController {

    @GET
    public List<TransferGift> getAllGifts() {
        List<Gift> gifts = GiftsManager.getAllGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        for (Gift g : gifts) {
            transfers.add(new TransferGift(g));
        }
        return transfers;
    }

    @GET
    @Path("/{title}")
    public TransferGift getGiftByTitle(@PathParam("title") String title) {
        return new TransferGift(GiftsManager.getGiftByTitle(title));
    }

    @GET
    @Path("/price/{price}")
    public List<TransferGift> getGiftsByPrice(@PathParam("price") int price){
        List<Gift> gifts = GiftsManager.getGiftsByPrice(price);
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        for (Gift g : gifts) {
            transfers.add(new TransferGift(g));
        }
        return transfers;
    }

    @GET
    @Path("/content/{content}")
    public List<TransferGift> getGiftsByContent(@PathParam("content") String content){
        List<Gift> gifts = GiftsManager.getGiftsByContent(content);
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        for (Gift g : gifts) {
            transfers.add(new TransferGift(g));
        }
        return transfers;
    }

    @POST
    public Response addGift(TransferGift g) {
        if(!GiftsManager.alreadyAddedGift(g.getTitle())) {
            GiftsManager.createGift(new Gift(g));
            return Response.status(Response.Status.CREATED).entity("Succesfully created").build();
        }
        else throw new AlreadyAddedException("The gift is already in the database");
    }

    @PUT
    public Response updateGiftByName(TransferGift newGift) {
        if(GiftsManager.alreadyAddedGift(newGift.getTitle())) {
            Gift gift = new Gift(newGift.getId(), newGift.getTitle(), newGift.getContent(), newGift.getPrice());
            GiftsManager.updateGift(gift);
            return Response.status(Response.Status.OK).entity("Succesfully updated").build();
        }
        else throw new DataNotFoundException("The gift does not exist in the database");
    }

    @DELETE
    public Response deleteGift(@HeaderParam("title") String title) {
        if(GiftsManager.alreadyAddedGift(title)) {
            GiftsManager.deleteGift(GiftsManager.getGiftByTitle(title));
            return Response.status(Response.Status.OK).entity("Deleted").build();
        }
        else throw new DataNotFoundException("The gift does not exist in the database");
    }
}
