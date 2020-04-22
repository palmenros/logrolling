package com.logrolling.server.exceptions;

import com.logrolling.server.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyAddedExceptionMapper implements ExceptionMapper<AlreadyAddedException> {

    public Response toResponse(AlreadyAddedException e) {
        //ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 406, "");
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
    }
}
