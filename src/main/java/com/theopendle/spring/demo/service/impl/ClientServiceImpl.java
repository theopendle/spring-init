package com.theopendle.spring.demo.service.impl;

import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * A simple service implementation for demonstration purposes.
 *
 * @author Theo Pendle
 */
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
