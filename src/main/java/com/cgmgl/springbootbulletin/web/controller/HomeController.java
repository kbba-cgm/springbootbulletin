package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cgmgl.springbootbulletin.bl.service.CategoryService;
import com.cgmgl.springbootbulletin.bl.service.PostService;

@Controller
public class HomeController {
	@Autowired
	PostService postService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public String dashboardView(Model m) {
		m.addAttribute("posts", postService.getAllPublicPosts());
		return "pages/dashboard";
	}

	@GetMapping("/home")
	public String homeView(Model m) {
		m.addAttribute("posts", postService.getAllPublicPosts());
		return "pages/home/newsfeed";
	}

	@GetMapping("/home/posts/{post-id}")
	public String postView(@PathVariable("post-id") Long id, Model m) {
		m.addAttribute("post", postService.findPostbyId(id));

		return "pages/home/post-view";
	}
}