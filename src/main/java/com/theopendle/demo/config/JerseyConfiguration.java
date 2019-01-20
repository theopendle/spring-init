package com.theopendle.demo.config;

import com.theopendle.demo.controller.ClientController;
import com.theopendle.demo.provider.GenericExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest") //
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(ClientController.class);
        register(GenericExceptionMapper.class);
    }
}
