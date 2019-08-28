package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.business.account.find.AccountFindByIdService;
import com.jullierme.revolut.business.account.find.AccountFindServiceFactory;
import com.jullierme.revolut.model.account.AccountMapper;
import com.jullierme.revolut.model.account.AccountMapperImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountFindByIdResource {
    private AccountFindByIdService findByIdService;
    private AccountMapper accountMapper;

    public AccountFindByIdResource() {
        createServices();
    }

    private void createServices() {
        findByIdService = AccountFindServiceFactory.getInstance();
        accountMapper = new AccountMapperImpl();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
            return findByIdService.find(id)
                    .map(entity -> Response
                            .status(Response.Status.OK)
                            .entity(accountMapper.toAccountDto(entity))
                            .build())
                    .orElse(Response
                            .status(Response.Status.NOT_FOUND)
                            .build());
    }
}