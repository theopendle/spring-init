package com.theopendle.demo.service.impl;

import com.theopendle.demo.model.Client;
import com.theopendle.demo.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public List<Client> getClients() {
        // Dummy implementation
        return Arrays.asList(
                new Client()
                        .setFirstName("John")
                        .setLastName("Doe"),
                new Client()
                        .setFirstName("Jane")
                        .setLastName("Doe")
        );
    }
}
