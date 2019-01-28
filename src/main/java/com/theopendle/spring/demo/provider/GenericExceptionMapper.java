package com.theopendle.spring.demo.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Catches exceptions in the execute of code behind a REST endpoint and returns an error response.
 *
 * @author Theo Pendle
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
}
