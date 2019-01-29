package com.theopendle.spring.demo.data.mapper;

import com.theopendle.spring.demo.model.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientMapperTest {

    @Autowired
    private ClientMapper mapper;

    @Test
    public void test_select_valid() {
        final Client client = mapper.selectOne(1);
        Assert.assertNotNull(client);
    }

    @Test
    public void test_insert_valid() {
        final Client client = buildValidClient();
        mapper.insertClient(client);

        Assert.assertNotNull(client.getId());
    }

    private Client buildValidClient() {
        return new Client()
                .setFirstName("John")
                .setLastName("Doe");
    }
}