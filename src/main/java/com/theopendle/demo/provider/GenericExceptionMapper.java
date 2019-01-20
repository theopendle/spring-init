package com.theopendle.demo.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

//
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
}
