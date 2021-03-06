package com.yj.bishe.demo.config;

import com.yj.bishe.demo.interceptor.OneInterceptor;
import com.yj.bishe.demo.interceptor.TwoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;


/**
 * 
 * @author 大大大
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Resource
	OneInterceptor oneInterceptor;
	@Resource
	TwoInterceptor twoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(oneInterceptor).addPathPatterns("/users/**");
		registry.addInterceptor(twoInterceptor).addPathPatterns("/admin/**");
	}
	
}
