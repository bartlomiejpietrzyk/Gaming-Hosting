package com.github.bartlomiejpietrzyk.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class Dashboard {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "/panel/dashboard";
    }
}
