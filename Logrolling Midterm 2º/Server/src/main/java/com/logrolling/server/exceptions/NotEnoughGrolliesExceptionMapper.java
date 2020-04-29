package com.logrolling.server.exceptions;

import com.logrolling.server.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughGrolliesExceptionMapper implements ExceptionMapper<NotEnoughGrolliesException> {

    public Response toResponse(NotEnoughGrolliesException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500, "");
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
