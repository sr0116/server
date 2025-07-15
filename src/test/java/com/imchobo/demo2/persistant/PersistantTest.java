package com.imchobo.demo2.persistant;

import com.imchobo.demo2.mapper.TestMapper;
import com.zaxxer.hikari.HikariConfig;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


    @SpringBootTest
    @Slf4j
    public class PersistantTest {
        @Autowired
        private HikariConfig hikariConfig;
        @Autowired
        private DataSource dataSource;
        @Autowired
        private TestMapper mapper;

        @Test
        public void testHikari() {
            log.info("hikari :: {}", this.hikariConfig);
        }

        @Test
        public void testDataSource() {
            log.info("ds :: {}", this.dataSource);
        }

        @Test
        public void testBoardMapper() {
            log.info("boardMapper :: {}", this.mapper);
        }
    }
