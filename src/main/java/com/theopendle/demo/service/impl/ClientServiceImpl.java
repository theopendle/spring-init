package com.theopendle.demo.service.impl;

import com.theopendle.demo.model.Client;
import com.theopendle.demo.service.ClientService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Override
    public List<Client> getClients() {
        // Dummy implementation
        return Arrays.asList(
                new Client()
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setDob(LocalDate.of(1992, 12, 6)),
                new Client()
                        .setFirstName("Jane")
                        .setLastName("Doe")
                        .setDob(LocalDate.of(1987, 4, 2))
        );
    }
}
