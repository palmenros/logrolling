package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.model.Gift;
import com.logrolling.server.transfer.TransferGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/gifts")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsController {

    @GET
    public List<TransferGift> getAllGifts() {
        // List<Gift> gifts = GiftsController.getGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        /*for (Gift g : gifts) {
            transfers.add(new TransferGift(f));*/
        return transfers;
    }

    @Path("/{title}")
    @GET
    public TransferGift getGift(@PathParam("title") String title) {
        return new TransferGift(GiftsManager.getGiftByTitle(title));
    }

    @GET
    @Path("/{title}/price")
    public int getPrice(@HeaderParam("title") String title) {
        return GiftsManager.getGiftByTitle(title).getPrice();
    }

    @POST
    public void createGift(TransferGift newGift) {
        GiftsManager.createGift(new Gift(newGift.getTitle(), newGift.getContent(), newGift.getPrice()));
    }

    @PUT
    public void updateGiftByName(TransferGift newGift) {
        Gift gift = new Gift(newGift.getId(), newGift.getTitle(), newGift.getContent(), newGift.getPrice());
        GiftsManager.updateGift(newGift.getId(), gift);
    }

    @DELETE
    public void deleteGift(@HeaderParam("title") String title) {
        GiftsManager.deleteGift(GiftsManager.getGiftByTitle(title));
    }
}
