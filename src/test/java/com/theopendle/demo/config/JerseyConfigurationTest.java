package com.theopendle.demo.config;

import com.theopendle.demo.controller.ClientController;
import com.theopendle.demo.provider.GenericExceptionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JerseyConfigurationTest {

    @Autowired
    private JerseyConfiguration config;

    @Test
    public void test_init() {
        Set<Class<?>> classes = config.getClasses();
        assertTrue(classes.contains(ClientController.class));
        assertTrue(classes.contains(GenericExceptionMapper.class));
    }
}