package com.logrolling.server.services.gifts;

import com.logrolling.server.services.authentication.AuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsWebServiceBroker {

    @GET
    public List<TransferGift> getAllGifts() {
        return Gift.getAllGifts();
    }

    @GET
    @Path("/{title}")
    public TransferGift getGiftByTitle(@PathParam("title") String title) {
        return Gift.getGiftByTitle(title);
    }

    @GET
    @Path("/purchased")
    public List<TransferPurchasedGift> getPurchasedGifts() {
        return Gift.getPurchasedGifts();
    }

    @POST
    @Path("@purchase/")
    public void purchaseGift(@HeaderParam("token") String token, TransferPurchase transferPurchase) {
        Gift.purchaseGift(token, transferPurchase.getTitle(), transferPurchase.getAddress());
    }

}
