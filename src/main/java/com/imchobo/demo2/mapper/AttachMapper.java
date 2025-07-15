package com.imchobo.demo2.mapper;

import java.util.List;

import com.imchobo.demo2.domain.Attach;

public interface AttachMapper {
	void insert(Attach attach);
	List<Attach> list(Long bno);
	Attach selectOne(String uuid);
	void delete(String uuid);
	void deleteByBno(Long bno);
}