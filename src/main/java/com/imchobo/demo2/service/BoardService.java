package com.imchobo.demo2.service;

import java.util.List;

import lombok.AllArgsConstructor;

import com.imchobo.demo2.domain.Board;
import com.imchobo.demo2.domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import com.imchobo.demo2.mapper.AttachMapper;
import com.imchobo.demo2.mapper.BoardMapper;
import com.imchobo.demo2.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class BoardService {

  private BoardMapper mapper;
  private AttachMapper attachMapper;
  private ReplyMapper replyMapper;
//  private AttachLinkMapper attachLinkMapper;

//
@Transactional
public List<Board> list(Criteria cri) {
  return mapper.list(cri); //1page 10개씩
}


//////	조회수 ////
@Transactional
public Board findBy(Long bno) {
mapper.increseCnt(bno);
return mapper.selectOne(bno);
}
//// 카운트 //
@Transactional
public long getCount (Criteria criteria){
  return mapper.getCount(criteria);
}
//// 수정  //
@Transactional
public void modify (Board board){
 mapper.update(board);
//기존 첨부파일 메타 데이터 삭제
//    attachMapper.deleteByBno(board.getBno());
 // 새로운 첨부파일 메타데이터
  board.getAttachs().forEach(a -> {
//            a.setBno(board.getBno());
        attachMapper.insert(a);
      });
}
//////
//    public static void main (String[]args){
//      new BoardService().list(new Criteria()).forEach(b -> log.info("{}", b.getTitle()));
//    }

// 글쓰기

@Transactional
public void write (Board board){
  Long bno = board.getBno();  // bno는 부모의 번호
  if (bno == null) {  // 여기는 답글 아님
    mapper.insert(board);   // 이 시점에 부여
    mapper.updateGrpMyself(board);
  } else { // 여기가 답글
    // 1. 부모글 조회
    Board parent = mapper.selectOne(bno);  // 부모글을 가져오는거
    // 내 위치에 작성하기 위한 update. 나보다 seq값을 더 밀어내야함

    // 2. maxSeq 취득
    // select
//    int maxSeq = mapper.selectMaxSeq(parent);
//    board.setSeq(maxSeq + 1); // 수정
//
//    // 3. 해당조건의 게시글들의 seq 밀어내기
//    board.setGrp(parent.getGrp());  // 확정
//    board.setDepth(parent.getDepth() + 1); // 확정
//    mapper.updateSeqIncease(board);  // 이제 한번 밀려남.

    // 4. insert
    mapper.insertChild(board);

  }

////			mapper.insert(board);
  board.getAttachs().forEach(a -> {
    a.setBno(board.getBno());
    attachMapper.insert(a);
  });
}
// 글 삭제
@Transactional
  public void remove (Long bno){
    replyMapper.deleteByBno(bno);

    attachMapper.deleteByBno(bno);
    mapper.delete(bno);

  }
}


