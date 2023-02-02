package com.mati.springschoolms.controllers;

import com.mati.springschoolms.config.service.UserService;
import com.mati.springschoolms.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/register/validate")
    public String validate(@ModelAttribute User user){
        userService.save(user);

        return "redirect:/login";
    }
}
