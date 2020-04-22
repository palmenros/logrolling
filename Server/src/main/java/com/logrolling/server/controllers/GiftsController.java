package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.model.Gift;
import com.logrolling.server.transfer.TransferGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

//Uncomment when GiftManager is done
/*
@Path("/gifts")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsController implements GiftDao{


    @GET
    @Override
    public List<TransferGift> getAllGifts() {
        // List<Gift> gifts = GiftsController.getGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        /*for (Gift g : gifts) {
            transfers.add(new TransferGift(f));
        }
        return transfers;
    }


    @GET
    @Path("/filter")
    public List<TransferFavor> getGiftsFromFilter(Filter filter) {
        List<Gifts> gifts = GiftsManager.getGiftsByFilter(filter);
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        for (Gift f : gifts) {
            transfers.add(new TransferGift(f));
        }
        return transfers;


    @Path("/{title}")
    @GET
    @Override
    public TransferGift getGift(@PathParam("title") String title) {
        return new TransferGift(GiftManager.getGiftByTitle(title));
    }

    @GET
    @Path("/{title}/price")
    @Override
    public TransferGift getPrice(@HeaderParam("title") String title) {
        return GiftManager.getGiftByTitle(title).getPrice();
    }

    @POST
    @Override
    public void createGift(TransferGift newGift) {
        UserManager.createGift(new Gift(title, content, price);
    }

    @PUT
    @Override
    public void updateGiftByName(TransferGift newGift) {
        Gift gift = new Gift(newGift.getId(), newGift.getTitle(), newGift.getContent(), newGift.getPrice());
        GiftManager.updateGiftbyTitle(newGift.getTitle(), gift);
    }

    @DELETE
    @Override
    public void deleteGift(@HeaderParam("title") String title) {
        GiftManager.deleteGiftByTitle(title);
    }
}
 */
