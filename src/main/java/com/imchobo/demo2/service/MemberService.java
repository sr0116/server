package com.imchobo.demo2.service;

import com.imchobo.demo2.util.PasswordEncoder;
import org.apache.ibatis.session.SqlSession;

import com.imchobo.demo2.domain.Member;
import lombok.extern.slf4j.Slf4j;
import com.imchobo.demo2.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {

	@Autowired
  private MemberMapper mapper;
  private PasswordEncoder passwordEncoder;


  public int register(Member member) {
    member.setPw(passwordEncoder.encode(member.getPw()));
    return mapper.insert(member);
	}
//
	public Member findByID(String id) {
      return mapper.findById(id);
	}
//
	public boolean login(String id, String pw) {
		Member member = findByID(id);
		if(member == null) {
			return false;
		}
		return passwordEncoder.matches(pw, member.getPw());
	}

}
