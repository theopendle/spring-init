package com.theopendle.spring.demo.datasource;

import com.theopendle.spring.demo.data.mapper.DataSourceHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataSourceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

    @Test
    public void test_rollback() {
        final Long before = count();
        try {
            // Insert one OK record and then one NOK record to cause exception
            dataSourceHelper.causeDataIntegrityViolationException();

            // Make sure we never get this far
            Assert.fail();

        } catch (final DataIntegrityViolationException e) {
            log.info("Caught desired exception: <{}>", e.getClass().getSimpleName());

            // Check no new records inserted
            Assert.assertEquals("No records should be inserted. Count should not change.", before, count());

        } catch (final Exception e) {
            // Check we have the right type of exception
            Assert.fail();
        }
    }

    @Test
    public void test_connectionPool() {
        final List<Connection> connections = new ArrayList<>();
        try {
            // Attempt to open one more connection than authorized
            for (int i = 0; i < maxPoolSize + 1; i++) {
                connections.add(dataSource.getConnection());
            }
        } catch (final SQLTransientConnectionException e) {
            log.info("Caught desired exception: <{}>", e.getClass().getSimpleName());

        } catch (final Exception e) {
            // Check we have the right type of exception
            Assert.fail();
        } finally {

            // Close all connections
            connections.forEach(connection -> {
                try {
                    connection.close();
                } catch (final SQLException e) {
                    log.error("Unexpected exception: could not close connection", e);
                }
            });
        }

        // Check we opened the right number of connections
        Assert.assertEquals(maxPoolSize, connections.size());
    }

    private Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM clients", Long.class);
    }

}

