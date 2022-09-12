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
import com.cgmgl.springbootbulletin.bl.service.PrincipalService;
import com.cgmgl.springbootbulletin.bl.service.RoleService;
import com.cgmgl.springbootbulletin.bl.service.UserService;

@Controller
public class ProfileController {
    @Autowired
    PrincipalService principalService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/profile/detail")
    public String profileDetail(Model m) {
        long loggedInId = principalService.getPrincipal().getId();
        m.addAttribute("roles", roleService.getAllRoles());
        m.addAttribute("user", userService.findUserbyId(loggedInId));
        return "pages/profile/detail";
    }

    @PostMapping("/profile/update")
    public String profileUpdate(@Valid @ModelAttribute("user") UserDto userDto, BindingResult br, Model m) {
        if(br.hasErrors()){
            m.addAttribute("roles", roleService.getAllRoles());
            return "pages/profile/detail";
        }

        UserDto updated_user = userService.updateUser(userDto);

        if(updated_user == null) {
            m.addAttribute("fail", "Incorrect credentials");
            return "pages/profile/detail";
        }

        principalService.getPrincipal().updatePrincipal(userDto);
        return "redirect:/profile/detail";
    }
}
