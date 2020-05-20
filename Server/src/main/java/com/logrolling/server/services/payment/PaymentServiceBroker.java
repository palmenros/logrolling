package com.logrolling.server.services.payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentServiceBroker {

    @GET
    public TransferPaymentToken getClientToken() {
        return Payment.getClientToken();
    }

    @POST
    public void makePaymentTransaction(TransferTransaction transferTransaction, @HeaderParam("token") String authToken) {
        Payment.makePaymentTransaction(transferTransaction, authToken);
    }

    int priceToGrollies(int priceInCents) {
        return Payment.priceToGrollies(priceInCents);
    }

    Map<Integer, Integer> getPricesMap() {
        return Payment.getPricesMap();
    }
}
