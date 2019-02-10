package com.theopendle.spring.demo.controller;

import com.theopendle.spring.demo.jersey.SafeCollection;
import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Slf4j
@Path("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Client getClient(@PathParam("id") final Long id) {
        return clientService.getClient(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public SafeCollection<Client> getClients(@QueryParam("firstName") final String firstName) {
        return new SafeCollection<>((
                firstName == null) ?
                clientService.getClients() :
                clientService.getClientsByFirstName(firstName)
        );
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createClient(final Client client) {
        clientService.createClient(client);
        return Response.ok().build();
    }
}
