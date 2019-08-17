package com.jullierme.revolut.business.transaction.resource;

import com.jullierme.revolut.business.transaction.find.TransactionFindByIdService;
import com.jullierme.revolut.business.transaction.find.TransactionFindServiceFactory;
import com.jullierme.revolut.model.transaction.TransactionMapper;
import com.jullierme.revolut.model.transaction.TransactionMapperImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transaction")
public class TransactionFindByIdResource {
    private TransactionFindByIdService findByIdService;
    private TransactionMapper transactionMapper;

    public TransactionFindByIdResource() {
        createServices();
    }

    private void createServices() {
        findByIdService = TransactionFindServiceFactory.instance().getTransactionFindByIdService();
        transactionMapper = new TransactionMapperImpl();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        try {
            return findByIdService.find(id)
                    .map(entity -> Response
                            .status(Response.Status.OK)
                            .entity(transactionMapper.toTransactionDto(entity))
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