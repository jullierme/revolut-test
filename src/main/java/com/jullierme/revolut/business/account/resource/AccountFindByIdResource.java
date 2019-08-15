package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.business.account.findById.AccountFindByIdService;
import com.jullierme.revolut.business.account.findById.AccountFindByIdServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountFindByIdResource {
    private AccountFindByIdService findByIdService;

    public AccountFindByIdResource() {
        createServices();
    }

    private void createServices() {
        findByIdService = new AccountFindByIdServiceFactory().getInstance();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        try {
            return findByIdService.find(id)
                    .map(entity -> Response
                            .status(Response.Status.OK)
                            .entity(entity)
                            .build())
                    .orElse(Response
                            .status(Response.Status.NOT_FOUND)
                            .build());
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("There was an internal server error")
                    .build();
        }
    }
}