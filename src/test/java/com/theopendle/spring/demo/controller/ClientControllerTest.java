package com.theopendle.spring.demo.controller;

import com.theopendle.spring.demo.DemoApplication;
import com.theopendle.spring.demo.jersey.SafeCollection;
import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest extends JerseyTest {

    @Autowired
    private ClientService clientService;

    private static final String BASE_PATH = "/clients";
    private static final String FIRST_NAME = "Theo";
    private static final String FIRST_NAME_PARAM = "firstName";

    @Test
    public void test_getClient_valid() {
        final Long id = 1L;
        final Response response = target(BASE_PATH).path(Long.toString(id)).request().get();

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        final Client actual = response.readEntity(Client.class);
        assertEquals(clientService.getClient(id), actual);
    }

    @Test
    public void test_getClients_valid() {
        final Response response = target(BASE_PATH).request().get();

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        final Collection<Client> actual = response.readEntity(new GenericType<SafeCollection<Client>>() {
        }).getCollection();
        assertEquals(clientService.getClients(), actual);
    }

    @Test
    public void test_getClientsByFirstName_valid() {
        final Response response = target(BASE_PATH).queryParam(FIRST_NAME_PARAM, FIRST_NAME).request().get();

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        final Collection<Client> actual = response.readEntity(new GenericType<SafeCollection<Client>>() {
        }).getCollection();
        assertEquals(clientService.getClientsByFirstName(FIRST_NAME), actual);
    }

    @Test
    public void test_createClient_valid() {
        final Client client = new Client()
                .setFirstName("Peter")
                .setLastName("Parker");
        final Response response = target(BASE_PATH).request().post(Entity.entity(client, MediaType.APPLICATION_JSON));

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
    }

    @Test
    public void test_createClient_invalidPayload() {
        final Response response = target(BASE_PATH).request().post(Entity.entity("Peter Parker", MediaType.APPLICATION_JSON));

        assertEquals(HttpStatus.BAD_REQUEST_400.getStatusCode(), response.getStatus());
    }

    @Override
    protected Application configure() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
        return new ResourceConfig(ClientController.class).property("contextConfig", context);
    }
}