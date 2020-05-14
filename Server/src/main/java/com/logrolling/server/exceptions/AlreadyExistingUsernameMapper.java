package com.logrolling.server.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyExistingUsernameMapper implements ExceptionMapper<AlreadyExistingUsername> {

    public Response toResponse(AlreadyExistingUsername e) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
    }

}