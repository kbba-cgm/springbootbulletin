package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String homeview() {
		return "pages/home";
	}
}
