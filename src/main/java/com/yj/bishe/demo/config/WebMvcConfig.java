package com.yj.bishe.demo.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                // 添加对外暴露的访问路径, /** 表示多级路径
//                .addResourceHandler("/static/**")
//                // 添加文件放置的目录
//                .addResourceLocations("classpath:/static/");
    }

}
