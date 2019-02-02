package com.theopendle.spring.demo.data.mapper;

import com.theopendle.spring.demo.model.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientMapperTest {

    @Autowired
    private ClientMapper mapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_select_valid() {
        final Client client = mapper.selectOne(1);
        Assert.assertNotNull(client);
    }

    @Test
    public void test_select_invalid() {
        final Client client = mapper.selectOne(-1);
        Assert.assertNull(client);
    }

    @Test
    public void test_insert_invalid() {
        final Client invalid = buildValidClient();
        mapper.insertClient(invalid);

        Assert.assertNotNull(invalid.getId());
    }

    @Test
    public void test_insert_valid() {
        final Client invalid = buildValidClient().setFirstName(null);
        try {
            mapper.insertClient(invalid);

            // Make sure we never get this far
            Assert.fail();
        } catch (final DataIntegrityViolationException e) {
            Assert.assertNull(invalid.getId());
        }
    }

    @Test
    public void test_findByFirstName_valid() {
        final List<Client> clients = mapper.findByFirstName("Theo");
        Assert.assertNotNull(clients);
        Assert.assertFalse(clients.isEmpty());
    }

    @Test
    public void test_findByFirstName_empty() {
        final List<Client> clients = mapper.findByFirstName("Notarealname");
        Assert.assertNotNull(clients);
        Assert.assertTrue(clients.isEmpty());
    }

    @Test
    public void test_findByFirstName_null() {
        final List<Client> clients = mapper.findByFirstName(null);
        Assert.assertNotNull(clients);
        Assert.assertTrue(clients.isEmpty());
    }

    private Client buildValidClient() {
        return new Client()
                .setFirstName("John")
                .setLastName("Doe");
    }
}