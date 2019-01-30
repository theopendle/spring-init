package com.theopendle.spring.demo.service.impl;

import com.theopendle.spring.demo.data.mapper.ClientMapper;
import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> getClients() {

        clientMapper.findByFirstName("Theo");

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
