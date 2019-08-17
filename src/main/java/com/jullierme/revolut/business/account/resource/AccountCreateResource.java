package com.jullierme.revolut.business.account.resource;

import com.jullierme.revolut.business.account.create.AccountCreateService;
import com.jullierme.revolut.business.account.create.AccountCreateServiceFactory;
import com.jullierme.revolut.model.account.Account;
import com.jullierme.revolut.model.account.AccountDto;
import com.jullierme.revolut.model.account.AccountMapper;
import com.jullierme.revolut.model.account.AccountMapperImpl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;

@Path("/account")
public class AccountCreateResource {
    @Context
    UriInfo uriInfo;

    private AccountCreateService accountCreateService;
    private AccountMapper accountMapper;

    public AccountCreateResource() {
        accountCreateService = AccountCreateServiceFactory.getInstance().getAccountCreateService();
        accountMapper = new AccountMapperImpl();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AccountDto dto) {
        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Account entity = accountMapper.toAccount(dto);

        try {
            entity = accountCreateService.create(entity);

            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + entity.getId()).build();

            return Response.created(uri).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}