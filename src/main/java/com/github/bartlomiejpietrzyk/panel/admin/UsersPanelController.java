package com.github.bartlomiejpietrzyk.panel.admin;

import com.github.bartlomiejpietrzyk.user.UserRepository;
import com.github.bartlomiejpietrzyk.user.UserService;
import com.github.bartlomiejpietrzyk.user.dto.UserDetailsDto;
import com.github.bartlomiejpietrzyk.user.dto.UserEditDto;
import com.github.bartlomiejpietrzyk.user.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/admin/user")
public class UsersPanelController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UsersPanelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String userList(Model model) {
//        model.addAttribute("usersList", userService.findAll());
        return "panel/adminUserList";
    }

    @ModelAttribute("usersList")
    public List<UserListDto> list() {
        return userService.findAll();
    }

    @GetMapping("/details")
    public String userDetails(@RequestParam String id, Model model) {
        UserDetailsDto detailsDto = userService.findUseretailsById(id);
        //todo payments counts n values
        model.addAttribute("user", detailsDto);
        return "panel/adminUserDetails";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam String id, Model model) {
        UserEditDto userById = userService.findUserById(id);
        model.addAttribute("user", userById);
        model.addAttribute("userId", id);
        return "panel/adminUserEdit";
    }

    @PostMapping("/edit")
    public String editForm(@ModelAttribute("user") @Valid UserEditDto userDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "redirect:/api/admin/user/edit?id=" + userDto.getId();
        }
        userService.editUpdate(userDto);
        return "redirect:/api/admin/user/edit?id=" + userDto.getId();
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUser(Long.valueOf(id));
        return "redirect:/api/admin/user/list";
    }
}
