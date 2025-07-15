package com.imchobo.demo2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.imchobo.demo2.domain.Category;

public interface CategoryMapper {
	
	@Select("SELECT * FROM  tbl_category tc where tc.status = 'ACTIVE' ORDER by odr")
	List<Category> list();
	

}
