package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.model.Gift;
import com.logrolling.server.transfer.TransferGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public TransferGift getGiftbyTitle(@PathParam("title") String title) {
        return new TransferGift(GiftsManager.getGiftByTitle(title));
    }

    @GET
    @Path("/price/{price}")
    public TransferGift getGiftbyPrice(@PathParam("price") int price){
        return new TransferGift(GiftsManager.getGiftByPrice(price));
    }

    @POST
    public void addGift(TransferGift g) {
        GiftsManager.createGift(new Gift(g));
    }

    @PUT
    public void updateGiftByName(TransferGift newGift) {
        Gift gift = new Gift(newGift.getId(), newGift.getTitle(), newGift.getContent(), newGift.getPrice());
        GiftsManager.updateGift(gift);
    }

    @DELETE
    public void deleteGift(@HeaderParam("title") String title) {
        GiftsManager.deleteGift(GiftsManager.getGiftByTitle(title));
    }
}
