package com.github.bartlomiejpietrzyk.panel.admin;

import com.github.bartlomiejpietrzyk.user.UserService;
import com.github.bartlomiejpietrzyk.user.dto.AdminUserEditDto;
import com.github.bartlomiejpietrzyk.user.dto.UserDetailsDto;
import com.github.bartlomiejpietrzyk.user.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/admin/user")
public class AdminUsersPanelController {

    private UserService userService;

    @Autowired
    public AdminUsersPanelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        return "panel/adminUserList";
    }

    @ModelAttribute("usersList")
    public List<UserListDto> list() {
        return userService.findAll();
    }

    @GetMapping("/details")
    public String userDetails(@RequestParam String id, Model model) {
        UserDetailsDto detailsDto = userService.findUserDetailsById(id);
        model.addAttribute("user", detailsDto);
        return "panel/adminUserDetails";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam String id, Model model) {
        AdminUserEditDto userById = userService.findUserById(id);
        model.addAttribute("user", userById);
        model.addAttribute("userId", id);
        return "panel/adminUserEdit";
    }

    @PostMapping("/edit")
    public String editForm(@ModelAttribute("user") @Valid AdminUserEditDto userDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "redirect:/api/admin/user/edit?id=" + userDto.getId();
        }
        userService.adminEditUpdate(userDto);
        return "redirect:/api/admin/user/edit?id=" + userDto.getId();
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/api/admin/user";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

}
