package com.imchobo.demo2.domain.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString

public class PageDto {
	private Criteria cri;
	private long total;
	
	private int start;
	private int end;
	
	private boolean left;
	private boolean right;
	
	private boolean doubleLeft;
	private boolean doubleRight;
	
	private int realEnd;
	public PageDto (Criteria cri, long total) {
		this.cri = cri;
		this.total = total;
		// 자바는 기본적으로 정수처리만 함 (1.5 -> 1로 계산)
		end = (cri.getPage() + 9) / 10 * 10;
		start = end - 9;
		realEnd = (int)((total + cri.getAmount() - 1) / cri.getAmount());
	
		if (end > realEnd) {
			end = realEnd;	
		}
		
		left = start > 1;
		right = end < realEnd;
		
		doubleLeft = cri.getPage() > 1;
		doubleRight = cri.getPage() < realEnd;
	}
//	public static void main(String[] args) {
//		log.info("{}", new PageDto(new Criteria(12, 10, 2), 123));
//	}
	
}
