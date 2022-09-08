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

import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.RoleService;
import com.cgmgl.springbootbulletin.bl.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

    @GetMapping("/users")
	public String allUser(Model m) {
		m.addAttribute("users", userService.getAllUsers());
		return "pages/users/lists";
	}

    @GetMapping("/users/{user-id}")
	public String showUser(@PathVariable("user-id") Long id, Model m) {
		m.addAttribute("user", userService.findUserbyId(id));
		return "pages/users/show";
	}

    @GetMapping("/users/create")
	public String createUser(Model m) {
		m.addAttribute("roles", roleService.getAllRoles());
		m.addAttribute("userDto", new UserDto());
		return "pages/users/create";
	}

	@PostMapping("/users/store")
	public String storeUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			m.addAttribute("roles", roleService.getAllRoles());
			return "pages/users/create";
		}

		userService.createUser(userDto);		
		return "redirect:/users";
	}

    @GetMapping("/users/{user-id}/edit")
	public String editUser(@PathVariable("user-id") Long id, Model m) {
		m.addAttribute("roles", roleService.getAllRoles());
		m.addAttribute("userDto", userService.findUserbyId(id));

		return "pages/users/edit";
	}

	@PostMapping("/users/update")
	public String updateUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult br, Model m) {
		if(br.hasErrors()){
			m.addAttribute("roles", roleService.getAllRoles());
			return "pages/users/edit";
		}

		userService.updateUser(userDto);		
		return "redirect:/users";
	}

	@PostMapping("/users/{user-id}/delete")
	public String deleteCategory(@PathVariable("user-id") Long id, Model m) {
		userService.deleteUserById(id);

		return "redirect:/users";
	}
}
