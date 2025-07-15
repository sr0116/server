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
public void list(Criteria cri) {
    List<Board> list = mapper.list(cri);
  }


//////	조회수 ////
@Transactional
public Board findBy(Long bno) {
mapper.increseCnt(bno);
return mapper.selectOne(bno);
}
//// 카운트 //
@Transactional
public long getCount (Criteria cri){
  return mapper.getCount(cri);
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
public void write(Board board) {
  Long bno = board.getBno();
  if (bno == null) {
    mapper.insert(board);
  }
  // 나중에 글번호 반환 필요할수도 있음

    board.getAttachs().forEach(a -> {
      a.setBno(board.getBno());
      attachMapper.insert(a);
      // 나중에 수정 사항 추가 하기(블로그에 있음)
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


