package com.logrolling.server.exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    public Response toResponse(UnauthorizedException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}