package com.imchobo.demo2.mapper;


import java.util.List;

import com.imchobo.demo2.domain.Member;


public interface MemberMapper {
	
	List<Member> select();
	
	Member findByNo(Long no);
	Member findById(String no);

    int insert(Member member);
    
    int delete(Long no);
    
    int update (Member member);

}
