package com.theopendle.spring.demo.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class GenericExceptionMapperTest {

    private static final String TEST_ERROR_MESSAGE = "Test message";

    @Test
    public void test_toResponse() {
        final GenericExceptionMapper genericExceptionMapper = new GenericExceptionMapper();
        final Response response = genericExceptionMapper.toResponse(new IllegalArgumentException(TEST_ERROR_MESSAGE));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(TEST_ERROR_MESSAGE, response.getEntity());
    }
}