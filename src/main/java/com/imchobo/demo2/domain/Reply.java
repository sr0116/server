package com.imchobo.demo2.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reply {
//	댓글
	

	private Long rno; // 번호
	private String content; // 내용
	private String id; // 아이디
	private String regdate; // 작성일시
	private Long bno; //  // 게시글 번호

}
