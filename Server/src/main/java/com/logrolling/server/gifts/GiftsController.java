package com.logrolling.server.gifts;

import com.logrolling.server.authentication.AuthenticableController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiftsController extends AuthenticableController {

    @GET
    public List<TransferGift> getAllGifts() {
        return Gift.getAllGifts();
    }

    @GET
    public TransferGift getGiftByTitle(@HeaderParam("title") String title) {
        return Gift.getGiftByTitle(title);
    }

    @GET
    @Path("/purchased")
    public List<TransferPurchasedGift> getPurchasedGifts(){
        return Gift.getPurchasedGifts();
    }
    
    @POST
    @Path("@purchase")
    public void purchaseGift(@HeaderParam("token") String token, @HeaderParam("title") String title,
                              @HeaderParam("address") String address) {
        String username = authenticateWithToken(token);
        Gift.purchaseGift(username, title, address);
    }

}
