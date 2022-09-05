package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    
    @GetMapping("/profile/detail")
    public String profileDetail() {
        return "pages/profile/detail";
    }
}
