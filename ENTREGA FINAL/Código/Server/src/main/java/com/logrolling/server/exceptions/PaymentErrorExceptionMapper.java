package com.logrolling.server.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PaymentErrorExceptionMapper implements ExceptionMapper<PaymentErrorException> {

    public Response toResponse(PaymentErrorException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}