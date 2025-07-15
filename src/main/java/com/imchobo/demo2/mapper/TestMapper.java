package com.imchobo.demo2.mapper;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {
    @Select("select now()")
    String now();
}
