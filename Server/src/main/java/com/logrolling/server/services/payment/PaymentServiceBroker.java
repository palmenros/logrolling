package com.logrolling.server.services.payment;

import com.braintreegateway.*;
import com.logrolling.server.exceptions.PaymentErrorException;
import com.logrolling.server.services.authentication.AuthenticationService;
import com.logrolling.server.services.users.TransferUser;
import com.logrolling.server.services.users.User;
import com.logrolling.server.services.users.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentServiceBroker {

    //TODO: Refactor

    private static BraintreeGateway gateway = new BraintreeGateway(
      Environment.SANDBOX,
      "3rq8z22jbdjwfjh5",
      "xzthpz3c33sprfp2",
      "76cf1dcc9561fc58dd7cb27db77e72cf"
    );

    @GET
    public TransferPaymentToken getClientToken() {
        String clientToken = gateway.clientToken().generate();

        return new TransferPaymentToken(clientToken);
    }

    @POST
    public void makePaymentTransaction(TransferTransaction transferTransaction, @HeaderParam("token") String authToken)
    {
        String username = AuthenticationService.authenticateWithToken(authToken);

        //Check if amount is a valid amount
        if(!getPricesMap().containsKey(transferTransaction.getAmount())) {
            throw new BadRequestException();
        }

        TransactionRequest request = new TransactionRequest()
          .amount(new BigDecimal(transferTransaction.getAmount()).divide(new BigDecimal(100)))
          .paymentMethodNonce(transferTransaction.getNonce())
          .options()
            .submitForSettlement(true).done();

        Result<Transaction> saleResult = gateway.transaction().sale(request);

        if (!saleResult.isSuccess()) {
            //Throw error
            throw new PaymentErrorException();
        } else {
            //Add grollies to user
            User user = UserManager.getUserByName(username);
            UserManager.updateUserGrollies(username, user.getGrollies() + priceToGrollies(transferTransaction.getAmount()));
        }
    }

    int priceToGrollies(int priceInCents) {
        return getPricesMap().get(priceInCents);
    }

    Map<Integer, Integer> getPricesMap() {
        Map<Integer, Integer> conversion = new HashMap<Integer, Integer>();
        conversion.put(99, 2000);
        conversion.put(499, 12000);
        conversion.put(1499, 40000);
        conversion.put(2999, 90000);
        conversion.put(5999, 200000);

        return conversion;
    }
}
