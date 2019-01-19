package com.theopendle.demo.service;

import com.theopendle.demo.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> getClients();
}
