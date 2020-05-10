package com.logrolling.server.controllers;

import com.logrolling.server.database.managers.GiftsManager;
import com.logrolling.server.exceptions.AlreadyAddedException;
import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;
import com.logrolling.server.exceptions.UnauthorizedException;
import com.logrolling.server.model.Gift;
import com.logrolling.server.model.PurchasedGift;
import com.logrolling.server.transfer.TransferGift;
import com.logrolling.server.transfer.TransferPurchasedGift;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsController extends AuthenticableController {

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
    public TransferGift getGiftByTitle(@HeaderParam("title") String title) {
        try {
            return new TransferGift(GiftsManager.getGiftByTitle(title));
        }
        catch(DataNotFoundException e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @GET
    @Path("/purchased")
    public List<TransferPurchasedGift> getPurchasedGifts(){
        List<PurchasedGift> purchased = GiftsManager.getPurchasedGifts();
        List<TransferPurchasedGift> transfers = new ArrayList<TransferPurchasedGift>();
        for(PurchasedGift p : purchased)
            transfers.add(new TransferPurchasedGift(p));
        return transfers;
    }
    
    @POST
    @Path("@purchase")
    public void purchasedGift(@HeaderParam("token") String token, TransferPurchasedGift gift){
        String username = authenticateWithToken(token);
        if(gift.getUsername().equals(username)) {
            // Gift was purchased by current user
            try {
                GiftsManager.purchaseGift(username, new PurchasedGift(gift));
            }
            catch(NotEnoughGrolliesException e){
                throw new NotEnoughGrolliesException();
            }
        }
        else {
            throw new UnauthorizedException();
        }
    }

}
