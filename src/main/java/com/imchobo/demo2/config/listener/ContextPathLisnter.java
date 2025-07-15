package com.imchobo.demo2.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.imchobo.demo2.mapper.CategoryMapper;
import com.imchobo.demo2.util.PropsLoaderUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class ContextPathLisnter implements ServletContextListener{

  @Autowired
	private CategoryMapper mapper;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("cp", sc.getContextPath()); //pbl

		//카테고리 정보
		sc.setAttribute("cate", mapper.list());

		Properties props = PropsLoaderUtil.getProperties("secret/aws_s3.properties");
		String s3url = String.format("https://%s.s3.%s.amazonaws.com/upload/", props.get("bucket-name"), props.get("region-name"));
		sc.setAttribute("s3url", s3url);


		
	}

}
