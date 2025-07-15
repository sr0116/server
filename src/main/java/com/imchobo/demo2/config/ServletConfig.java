package com.imchobo.demo2.config;

import com.imchobo.demo2.config.filter.CorsFilter;
import com.imchobo.demo2.config.filter.EncodeFilter;
import com.imchobo.demo2.config.listener.ContextPathLisnter;
import com.imchobo.demo2.mapper.CategoryMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;

@Configuration
public class ServletConfig {

//    @Autowired
//    private CategoryMapper categoryMapper;

    @Bean
    public FilterRegistrationBean<EncodeFilter> encodeFilterRegi(EncodeFilter encodeFilter) {
        FilterRegistrationBean<EncodeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(encodeFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return  registrationBean;

    }

    public FilterRegistrationBean<CorsFilter> corsFilterRegi(CorsFilter corsFilter) {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return  registrationBean;

    }
}

