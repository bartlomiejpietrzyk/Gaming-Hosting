package com.github.bartlomiejpietrzyk.panel.admin;

import com.github.bartlomiejpietrzyk.registration.UserCreateDto;
import com.github.bartlomiejpietrzyk.registration.UserRegistrationService;
import com.github.bartlomiejpietrzyk.user.Role;
import com.github.bartlomiejpietrzyk.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/admin/user/addUser")
public class UserCreateController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserCreateController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @ModelAttribute("user")
    public UserCreateDto userCreateDto() {

        return new UserCreateDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "panel/adminUserAddUser";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserCreateDto userDto,
                                      BindingResult result) {

        User existingEmail = userRegistrationService.findByEmail(userDto.getEmail());
        if (existingEmail != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "panel/adminUserAddUser";
        }
        userRegistrationService.save(userDto);
        return "redirect:/api/admin/user/addUser?success";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @ModelAttribute("roleList")
    public List<Role> rolesList() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        roles.add(new Role("ROLE_ADMIN"));
        return roles;
    }
}
