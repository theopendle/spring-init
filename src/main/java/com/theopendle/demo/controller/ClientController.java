package com.theopendle.demo.controller;

import com.theopendle.demo.model.Client;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/clients")
public class ClientController {

    @GET
    @Produces(APPLICATION_JSON)
    //TODO: make safe
    private List<Client> getClients() {
        return null;
    }
}
