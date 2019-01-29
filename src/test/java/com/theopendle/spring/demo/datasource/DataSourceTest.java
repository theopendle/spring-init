package com.theopendle.spring.demo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataSourceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test_rollback() {
        log.debug(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM clients", Long.class).toString());
    }

}

