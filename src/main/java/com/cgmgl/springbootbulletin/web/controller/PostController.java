package com.cgmgl.springbootbulletin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cgmgl.springbootbulletin.bl.dto.PostDto;
import com.cgmgl.springbootbulletin.bl.service.PostService;
import com.cgmgl.springbootbulletin.persistence.entity.Post;

@Controller
public class PostController {
    @Autowired
	PostService postService;


    @GetMapping("/posts")
	public String allPost(Model m) {
		m.addAttribute("posts", postService.getAllPosts());
		return "pages/posts/lists";
	}

    @GetMapping("/posts/{post-id}")
	public String showPost(@PathVariable("post-id") Long id, Model m) {
		PostDto post = postService.findPostbyId(id);
		String date = new SimpleDateFormat("MM/dd/yyyy").format(post.getCreated_at());
		m.addAttribute("post", post);
		m.addAttribute("date", date);
		return "pages/posts/show";
	}

    @GetMapping("/posts/create")
	public String createPost(Model m) {
		m.addAttribute("postDto", new PostDto());
		return "pages/posts/create";
	}

	@PostMapping("/posts/store")
	public String storePost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult br, Model m) {
		if(br.hasErrors())
			return "pages/posts/create";

		postService.createPost(postDto);		
		return "redirect:/posts";
	}

    @GetMapping("/posts/{post-id}/edit")
	public String editPost(@PathVariable("post-id") Long id, Model m) {
		m.addAttribute("postDto", postService.findPostbyId(id));

		return "pages/posts/edit";
	}

	@PostMapping("/posts/update")
	public String updatePost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult br, Model m) {
		if(br.hasErrors())
			return "pages/posts/edit";

		postService.updatePost(postDto);		
		return "redirect:/posts";
	}

	@PostMapping("/posts/{post-id}/delete")
	public String deleteCategory(@PathVariable("post-id") Long id, Model m) {
		postService.deletePostById(id);

		return "redirect:/posts";
	}
}
