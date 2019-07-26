package com.github.bartlomiejpietrzyk.panel.admin;

import com.github.bartlomiejpietrzyk.user.UserDto;
import com.github.bartlomiejpietrzyk.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin/")
public class UserEdit {
    private UserService userService;

    @Autowired
    public UserEdit(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String userUpdate(@RequestParam String id,
                             Model model) {
        UserDto userDto = userService.findUserById(id);
        model.addAttribute("user", userDto);
        return "panel/adminUserEdit";
    }

    @PostMapping("/edit")
    public String userUpdated(@ModelAttribute("user") @Valid UserEditDto userDto, BindingResult result) {
        return null;
    }
}
