package com.jullierme.revolut.config.exceptionMapping;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper  implements ExceptionMapper<NotFoundException> {

    public Response toResponse(NotFoundException exception){

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Resource not found!").build();
    }
}