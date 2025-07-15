package com.imchobo.demo2.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Review {
	
	private Long rno; // 리뷰 번호
	private String content; // 내용
	private String regdate; // 작성일시
	private Integer rating; // 별점
	private String writer; // 작성자
	private Long pno;  // 상품 번호
	

}
