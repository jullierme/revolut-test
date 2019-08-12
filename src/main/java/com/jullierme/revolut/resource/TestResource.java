package com.jullierme.revolut.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@Path("/test")
public class TestResource {
    @GET
    @Path("/nice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Integer id) {
        Map<String, Object> test = new HashMap<>();
        test.put("aa", "Nice");
        test.put("id", id);

        return Response.status(200).entity(test).build();
    }
}