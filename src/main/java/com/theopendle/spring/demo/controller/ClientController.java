package com.theopendle.spring.demo.controller;

import com.theopendle.spring.demo.jersey.SafeCollection;
import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Slf4j
@Path("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GET
    @Produces(APPLICATION_JSON)
    public SafeCollection<Client> getClients(@QueryParam("firstName") final String firstName) {
        return new SafeCollection<>((
                firstName == null) ?
                clientService.getClients() :
                clientService.getClientsByFirstName(firstName)
        );
    }
}
