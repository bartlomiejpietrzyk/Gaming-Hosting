package com.github.bartlomiejpietrzyk.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String logged() {
        return "redirect:/api/dashboard";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "panel/dashboard";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/service")
    public String service() {
        return "service";
    }

}
