package com.cgmgl.springbootbulletin.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.RoleService;
import com.cgmgl.springbootbulletin.bl.service.UserService;

@Controller
public class AuthController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;
    
    @GetMapping("/login")
    public String showLogin(Model m) {
        // m.addAttribute("user", new UserDto());
        return "pages/auth/login";
    }

    @GetMapping("/register")
    public String showRegister(Model m) {
        m.addAttribute("user", new UserDto());
        return "pages/auth/register";
    }

    @PostMapping("/register")
	public String storeUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult br, Model m) {
		if(br.hasErrors())
			return "pages/auth/register";

		userService.createUser(userDto);		
		return "redirect:/";
	}

}
