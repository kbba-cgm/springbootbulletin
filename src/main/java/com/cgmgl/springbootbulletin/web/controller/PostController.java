package com.cgmgl.springbootbulletin.web.controller;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cgmgl.springbootbulletin.bl.dto.CategoryDto;
import com.cgmgl.springbootbulletin.bl.dto.PostDto;
import com.cgmgl.springbootbulletin.bl.service.CategoryService;
import com.cgmgl.springbootbulletin.bl.service.PostService;
import com.cgmgl.springbootbulletin.bl.service.PrincipalService;

@Controller
public class PostController {
    @Autowired
	PostService postService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PrincipalService principalService;

    @GetMapping("/posts")
	public String allPost(Model m) {
		m.addAttribute("posts", postService.getAllPosts());
		return "pages/posts/lists";
	}

	@GetMapping("/user/posts")
	public String myPosts(Model m) {
		m.addAttribute("posts", postService.getOwnPosts());
		return "pages/posts/own-lists";
	}

    @GetMapping({"/posts/{post-id}", "user/posts/{post-id}"})
	public String showPost(@PathVariable("post-id") Long id, Model m) {
		PostDto post = postService.findPostbyId(id);
		String date = new SimpleDateFormat("MM/dd/yyyy").format(post.getCreated_at());
		m.addAttribute("post", post);
		m.addAttribute("date", date);
		return "pages/posts/show";
	}

    @GetMapping({"/posts/create", "/user/posts/create"})
	public String createPost(Model m) {
		m.addAttribute("categories", categoryService.getAllCategories());
		m.addAttribute("postDto", new PostDto());
		return "pages/posts/create";
	}

	@PostMapping("/posts/store")
	public String storePost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			m.addAttribute("categories", categoryService.getAllCategories());
			return "pages/posts/create";
		}

		postService.createPost(postDto);
		return redirectPostPageByRole();
	}

    @GetMapping({"/posts/{post-id}/edit" , "user/posts/{post-id}/edit"})
	public String editPost(@PathVariable("post-id") Long id, Model m) {
		PostDto postDto = postService.findPostbyId(id);
		int size = postDto.getCategoryDtos().size();
		long[] ids = new long[size];
		int i = 0;
		for (CategoryDto c : postDto.getCategoryDtos()) {
			ids[i++] = c.getId();
		}
		postDto.setCategoryIds(ids);

		m.addAttribute("categories", categoryService.getAllCategories());
		m.addAttribute("postDto", postDto);

		return "pages/posts/edit";
	}

	@PostMapping("/posts/update")
	public String updatePost(@Valid @ModelAttribute("postDto") PostDto postDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			m.addAttribute("categories", categoryService.getAllCategories());
			return "pages/posts/edit";
		}

		postService.updatePost(postDto);		
		return redirectPostPageByRole();
	}

	@PostMapping("/posts/{post-id}/delete")
	public String deleteCategory(@PathVariable("post-id") Long id, Model m) {
		postService.deletePostById(id);

		return redirectPostPageByRole();
	}

	private String redirectPostPageByRole(){
		if(principalService.isAdmin())		
			return "redirect:/posts";

		return "redirect:/user/posts";
	}
}
