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
    public List<TransferGift> getGifts(){
        // List<Gift> gifts = GiftsController.getGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        /*for (Gift g : gifts) {
            transfers.add(new TransferGift(f));
        }*/
        return transfers;
    }

    /*
    @POST
    public void createGift(String title, String content, int price){
        // UserManager.createGift(new Gift(title, content, price);
    }



    @PUT
    public void updateGiftByName(TransferGift newGift){
        Gift gift = new Gift(newGift.getId(), newGift.getTitle(), newGift.getContent(), newGift.getPrice());
        //GiftManager.updateGiftbyTitle(newGift.getTitle(), gift);
    }



    @DELETE
    public void deleteGift(@HeaderParam("gift") String title){
        //GiftManager.deleteGiftByTitle(title);
    }




    @Path("/{title}")
    @GET
    public TransferGift getGiftByName(@PathParam("title") String title){
        //return new TransferGift(GiftManager.getGiftByTitle(title));
    }



    @GET
    @Path("/price")
    public int getPrice(@HeaderParam("gift") String title){
        //return GiftManager.getGiftByTitle(title).getPrice();
    }
    */

}
