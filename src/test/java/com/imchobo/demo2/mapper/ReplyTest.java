package com.imchobo.demo2.mapper;

import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;


@SpringBootTest
public class ReplyTest {
    private static final Logger log = LoggerFactory.getLogger(ReplyTest.class);
    @Autowired
    ReplyMapper replyMapper;


    @Test
    public void testExist(){
        Assertions.assertNotNull(replyMapper);
        log.info("{}", replyMapper );
    }

}







































