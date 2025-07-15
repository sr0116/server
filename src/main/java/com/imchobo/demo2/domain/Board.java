package com.imchobo.demo2.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Alias("board")
public class Board {
//	게시글
	

	private Long bno; //  회원 번호
	

	private String title; // 제목
	private String content; // 게시글
	private String id; // 아이디
	private String regdate; // 작성일시
	private String moddate; // 
	private Integer cnt; // 
	private Integer cno; // 
	private int replyCnt; // 
	private int attachCnt; // 
	
	
	
	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno, int replyCnt , int attachCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
		this.replyCnt = replyCnt;
		this.attachCnt = attachCnt;
	}



	@Builder.Default
	private List<Attach> attachs = new ArrayList<>();

}
