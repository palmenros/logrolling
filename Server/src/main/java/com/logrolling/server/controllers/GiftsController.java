package com.logrolling.server.controllers;

import com.logrolling.server.transfer.TransferGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/gifts")
public class GiftsController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransferGift> getGifts(){
        // List<Gift> gifts = GiftsController.getGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        /*for (Gift g : gifts) {
            transfers.add(new TransferGift(f));
        }*/
        return transfers;
    }

}
