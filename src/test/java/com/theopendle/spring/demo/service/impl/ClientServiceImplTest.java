package com.theopendle.spring.demo.service.impl;

import com.theopendle.spring.demo.data.mapper.ClientMapper;
import com.theopendle.spring.demo.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

    @Mock
    private ClientMapper mapper;

    @InjectMocks
    private ClientServiceImpl service;

    @Test
    public void test_getClient() {
        final long id = 1L;
        service.getClient(id);
        Mockito.verify(mapper).selectOne(id);
    }

    @Test
    public void test_getClients() {
        service.getClients();
        Mockito.verify(mapper).findAll();
    }

    @Test
    public void test_getClientsByFirstName() {
        final String name = "Theo";
        service.getClientsByFirstName(name);
        Mockito.verify(mapper).findByFirstName(name);
    }

    @Test
    public void test_createClient() {
        final Client client = new Client()
                .setId(1L)
                .setFirstName("Theo")
                .setLastName("Pendle");
        service.createClient(client);
        Mockito.verify(mapper).insertClient(client);
    }
}