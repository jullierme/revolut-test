package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.business.transaction.create.TransactionCreateService;
import com.jullierme.revolut.business.transaction.create.TransactionCreateServiceFactory;
import com.jullierme.revolut.model.transaction.Transaction;
import com.jullierme.revolut.model.transaction.TransactionRequest;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;

@Path("/transaction")
public class TransactionCreateResource {
    @Context
    UriInfo uriInfo;

    private TransactionCreateService transactionCreateService;

    public TransactionCreateResource() {
        createServices();
    }

    private void createServices() {
        transactionCreateService = TransactionCreateServiceFactory.getInstance();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TransactionRequest request) {
        try {
            Transaction entity = transactionCreateService.create(request);

            URI uri = uriInfo.getAbsolutePathBuilder().path("/" + entity.getId()).build();

            return Response.created(uri).build();
        } catch (SQLException | IllegalArgumentException | IllegalStateException | NotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}