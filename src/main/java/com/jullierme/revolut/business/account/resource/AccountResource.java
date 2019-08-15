package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.business.account.findById.AccountFindByIdRestResponseService;
import com.jullierme.revolut.business.account.findById.AccountFindByIdService;
import com.jullierme.revolut.business.account.findById.AccountFindByIdServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountResource {
    private AccountFindByIdRestResponseService findByIdService;

    public AccountResource() {
        createServices();
    }

    private void createServices() {
        findByIdService = new AccountFindByIdServiceFactory().getFindByIdRestResponseInstance();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        return findByIdService.restFind(id);
    }
}