package com.example.reservation.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }




}
