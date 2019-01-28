package com.theopendle.spring.demo.service;

import com.theopendle.spring.demo.model.Client;

import java.util.List;

/**
 * A simple service interface for demonstration purposes.
 *
 * @author Theo Pendle
 */
public interface ClientService {
    List<Client> getClients();
}
