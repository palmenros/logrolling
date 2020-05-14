package com.logrolling.server.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughGrolliesExceptionMapper implements ExceptionMapper<NotEnoughGrolliesException> {

    public Response toResponse(NotEnoughGrolliesException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
