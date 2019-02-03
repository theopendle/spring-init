package com.theopendle.spring.demo.datasource;

import com.theopendle.spring.demo.data.mapper.DataSourceHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataSourceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Test
    public void test_rollback() {
        final Long before = count();
        try {
            // Insert one OK record and then one NOK record to cause exception
            dataSourceHelper.insertClients();

            // Make sure we never get this far
            Assert.fail();

        } catch (final DataIntegrityViolationException e) {
            log.info("Caught desired exception: <{}>", e.getClass().getSimpleName());

            // Check no new records inserted
            Assert.assertEquals("No records should be inserted. Count should not change.", before, count());
        }
    }

    private Long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM clients", Long.class);
    }

}

