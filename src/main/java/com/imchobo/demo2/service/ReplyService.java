package com.imchobo.demo2.service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import com.imchobo.demo2.domain.Reply;
import com.imchobo.demo2.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyService {
  private ReplyMapper mapper;

  public Reply findBy(Long rno) {
    return mapper.selectOne(rno);
  }
  //
  public List<Reply> list(Long bno, Long lastRno) {
    return mapper.list(bno, lastRno);
  }
  //
  public void register(Reply Reply) {
    mapper.insert(Reply);
  }
  //
  public void modify(Reply Reply) {
    mapper.update(Reply);
  }
  //
  public void remove(Long rno) {
    mapper.delete(rno);
  }
}
