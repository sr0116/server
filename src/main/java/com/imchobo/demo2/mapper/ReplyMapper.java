package com.imchobo.demo2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imchobo.demo2.domain.Reply;

public interface ReplyMapper {
	
	void insert(Reply reply);
	void update(Reply reply);
	void delete(Long rno);
	void deleteByBno(Long bno);

	
	Reply selectOne(Long rno);
	List<Reply> list(@Param("bno")  Long bno, @Param("lastRno") Long lastRno);
}


