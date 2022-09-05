package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    
    @GetMapping("/posts")
	public String allPost() {
		return "pages/posts/lists";
	}

    @GetMapping("/posts/{post-id}")
	public String showPost() {
		return "pages/posts/show";
	}

    @GetMapping("/posts/create")
	public String createPost() {
		return "pages/posts/create";
	}

    @GetMapping("/posts/{post-id}/edit")
	public String editPost() {
		return "pages/posts/edit";
	}
}
