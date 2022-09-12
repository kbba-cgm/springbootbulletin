package com.cgmgl.springbootbulletin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cgmgl.springbootbulletin.web.Interceptor.PostActionInterceptor;
import com.cgmgl.springbootbulletin.web.Interceptor.PostInterceptor;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    PostInterceptor postInterceptor;

    @Autowired
    PostActionInterceptor postActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(postInterceptor).addPathPatterns("/home/posts/*");
        registry.addInterceptor(postActionInterceptor).addPathPatterns("/user/posts/**");
    }
    
}
