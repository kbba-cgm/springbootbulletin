package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/users")
	public String allUser() {
		return "pages/users/lists";
	}

    @GetMapping("/users/{user-id}")
	public String showUser() {
		return "pages/users/show";
	}

    @GetMapping("/users/create")
	public String createUser() {
		return "pages/users/create";
	}

    @GetMapping("/users/{user-id}/edit")
	public String editUser() {
		return "pages/users/edit";
	}
}
