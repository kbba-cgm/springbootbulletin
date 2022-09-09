package com.cgmgl.springbootbulletin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cgmgl.springbootbulletin.bl.service.Impl.MyRequestInterceptor;

@Configuration
public class PostInterceptor implements WebMvcConfigurer {
    @Autowired
    MyRequestInterceptor myRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(myRequestInterceptor).addPathPatterns("/home/posts/*");
    }
    
}
