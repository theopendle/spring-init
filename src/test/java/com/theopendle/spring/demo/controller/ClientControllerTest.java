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

    @Test
    public void test_getClients_valid() {
        final Response response = target(BASE_PATH).request().get();

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        final Collection<Client> actual = response.readEntity(new GenericType<SafeCollection<Client>>() {
        }).getCollection();
        assertEquals(clientService.getClients(), actual);
    }

    @Override
    protected Application configure() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
        return new ResourceConfig(ClientController.class).property("contextConfig", context);
    }
}