package com.github.bartlomiejpietrzyk.panel.admin;

import com.github.bartlomiejpietrzyk.user.UserDto;
import com.github.bartlomiejpietrzyk.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/admin/user")
public class UsersList {
    private UserService userService;

    @Autowired
    public UsersList(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public String userList(Model model) {
        List<UserDto> all = userService.findAll();
        model.addAttribute("usersList", all);
        return "panel/adminUserList";
    }
}
