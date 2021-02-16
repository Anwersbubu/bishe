package com.yj.bishe.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
@MapperScan(basePackages = "com.yj.bishe.demo.dao")
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor interceptor = new PaginationInterceptor();
		
		
		
		return interceptor;
	}
	
}
