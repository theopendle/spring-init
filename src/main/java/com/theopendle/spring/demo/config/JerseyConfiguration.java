package com.theopendle.spring.demo.config;

import com.theopendle.spring.demo.controller.ClientController;
import com.theopendle.spring.demo.provider.GenericExceptionMapper;
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
