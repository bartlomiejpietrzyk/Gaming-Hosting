package com.github.bartlomiejpietrzyk.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api")
public class Dashboard {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/panel/dashboard";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
