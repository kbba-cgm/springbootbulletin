package com.cgmgl.springbootbulletin.web.controller;

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
import com.cgmgl.springbootbulletin.bl.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;

    @GetMapping("/categories")
	public String allCategory(Model m) {
		m.addAttribute("categories", categoryService.getAllCategories());
		return "pages/categories/lists";
	}

    @GetMapping("/categories/create")
	public String createCategory(Model m) {
		CategoryDto categoryDto = new CategoryDto();
		m.addAttribute("categoryDto", categoryDto);
		return "pages/categories/create";
	}

	@PostMapping("/categories/store")
	public String storeCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			return "pages/categories/create";
		}
		m.addAttribute("success", "New category created successfully.");
		categoryService.createCategory(categoryDto);

		return "redirect:/categories";
	}

    @GetMapping("/categories/{category-id}/edit")
	public String editCategory(@PathVariable("category-id") Long id, Model m) {
		m.addAttribute("categoryDto", categoryService.findCategorybyId(id));

		return "pages/categories/edit";
	}

	@PostMapping("/categories/update")
	public String updateCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			return "pages/categories/edit";
		}
		m.addAttribute("success", "New category updated successfully.");
		categoryService.updateCategory(categoryDto);

		return "redirect:/categories";
	}

	@PostMapping("/categories/{category-id}/delete")
	public String deleteCategory(@PathVariable("category-id") Long id, Model m) {
		categoryService.deleteCategoryById(id);
		m.addAttribute("success", "Category deleted successfully.");

		return "redirect:/categories";
	}
}
