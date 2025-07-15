package com.imchobo.demo2.mapper;

import com.imchobo.demo2.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberTest {

    @Autowired
    MemberMapper memberMapper;


    @Test
    public void testExist(){
        Assertions.assertNotNull(memberMapper);
        log.info("{}", memberMapper );
    }

    @Test
    public void testSelectone(){

        Member member = memberMapper.findById("sea");
        log.info("{}", member);
    }

}


