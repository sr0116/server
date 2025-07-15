package com.imchobo.demo2.domain;

import org.apache.ibatis.type.Alias;

import com.imchobo.demo2.domain.en.CategoryStatus;
import com.imchobo.demo2.domain.en.CategoryViewType;
import lombok.Builder;
import lombok.Data;


@Alias("category")
@Data
@Builder
public class Category {
	
	private Long cno;
	private String cname;
	private String regdate;
	private CategoryViewType cViewType;
	private int odr;
	private CategoryStatus status;

}
