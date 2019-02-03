package com.theopendle.spring.demo.service.impl;

import com.theopendle.spring.demo.data.mapper.ClientMapper;
import com.theopendle.spring.demo.model.Client;
import com.theopendle.spring.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A simple service implementation for demonstration purposes.
 *
 * @author Theo Pendle§
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> getClients() {
        return clientMapper.findAll();
    }

    @Override
    public List<Client> getClientsByFirstName(final String firstName) {
        return clientMapper.findByFirstName(firstName);
    }
}
