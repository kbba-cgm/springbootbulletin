package com.cgmgl.springbootbulletin.web.Interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.cgmgl.springbootbulletin.bl.dto.PostDto;
import com.cgmgl.springbootbulletin.bl.service.PostService;

@Component
public class PostInterceptor implements HandlerInterceptor {
    @Autowired
    PostService postService;

    private Long id;

    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 

        if (pathVariables.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/denied");
			return false;
        }
            
        try {
            id = Long.parseLong(pathVariables.get("post-id"));
        } catch (NumberFormatException nfe) {
            response.sendRedirect(request.getContextPath() + "/denied");
			return false;
        }

		PostDto postDto = postService.findPostbyId(id);
		if(!postDto.isPublished())
			response.sendRedirect(request.getContextPath() + "/denied");
	
        return true;
    }
}