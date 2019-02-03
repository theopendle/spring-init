package com.theopendle.spring.demo.data.mapper.impl;

import com.theopendle.spring.demo.data.mapper.ClientMapper;
import com.theopendle.spring.demo.data.mapper.DataSourceHelper;
import com.theopendle.spring.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataSourceHelperImpl implements DataSourceHelper {

    @Autowired
    private ClientMapper mapper;

    @Transactional
    @Override
    public void causeDataIntegrityViolationException() {
        // Create client OK to insert
        final Client ok = new Client()
                .setFirstName("John")
                .setLastName("Doe");

        // Create client that will cause exception on insert
        final Client nok = new Client();

        mapper.insertClient(ok);
        mapper.insertClient(nok);
    }
}
