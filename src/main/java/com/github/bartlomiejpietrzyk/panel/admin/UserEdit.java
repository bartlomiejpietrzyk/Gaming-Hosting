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
@RequestMapping("/api/admin/user")
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

    @PutMapping("/edit")
    public String userUpdated(@ModelAttribute("user") @Valid UserEditDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return "/edit";
        }
        userService.update(userDto);
        return "panel/dashboard";
    }
    @DeleteMapping("/delete")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "redirect:/api/admin/user/list";
    }
}
