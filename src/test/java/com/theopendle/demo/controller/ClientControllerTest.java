package com.theopendle.demo.controller;

import com.theopendle.demo.DemoApplication;
import com.theopendle.demo.model.Client;
import com.theopendle.demo.service.ClientService;
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
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest extends JerseyTest {

    @Autowired
    private ClientService clientService;

    private static final String BASE_PATH = "/clients";

    @Test
    public void test_getClients_valid() throws IOException {
        Response response = target(BASE_PATH).request().get();

        assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
        assertEquals(clientService.getClients(), response.readEntity(new GenericType<List<Client>>() {
        }));
    }

    @Override
    protected Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
        return new ResourceConfig(ClientController.class).property("contextConfig", context);
    }
}