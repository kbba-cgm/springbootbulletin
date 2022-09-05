package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/categories")
	public String allCategory() {
		return "pages/categories/lists";
	}

    @GetMapping("/categories/create")
	public String createCategory() {
		return "pages/categories/create";
	}

    @GetMapping("/categories/{category-id}/edit")
	public String editCategory() {
		return "pages/categories/edit";
	}
}
