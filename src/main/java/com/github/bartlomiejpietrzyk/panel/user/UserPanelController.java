package com.github.bartlomiejpietrzyk.panel.user;

import com.github.bartlomiejpietrzyk.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/user")
public class UserPanelController {
    private UserService userService;

    @Autowired
    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    //todo get userid from sess
    //todo make table with data for user
    //todo make edit form for userdata
    //todo make sites for servers n payments


    @GetMapping("/myProfile")
    public String showUserProfile() {
        return "panel/userProfile";
    }

    @GetMapping("/myServers")
    public String showUserServers() {
        return "panel/userServersList";
    }

    @GetMapping("/myPayments")
    public String showUserPayments() {
        return "panel/userPaymentsList";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

}
