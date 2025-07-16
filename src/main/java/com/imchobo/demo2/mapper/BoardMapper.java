package com.imchobo.demo2.mapper;

import java.util.List;

import com.imchobo.demo2.domain.Board;
import com.imchobo.demo2.domain.dto.Criteria;

public interface BoardMapper {

	
	
	List<Board> list(Criteria cri);
  
	Board selectOne(Long bno);
	
	void insert(Board board);
	
	long getCount(Criteria cri);

	void update(Board board);
	
	void delete(Long bno);
	
	void increseCnt(Long bno);

	void updateGrpMyself(Board board);

	void updateSeqIncrease(Board parent);

	void insertChild(Board board);

	int selectMaxSeq(Board parent);

	


}
