package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartlomiejpietrzyk.dto.UserEditDto;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.UserRepository;
import pl.bartlomiejpietrzyk.service.UserEditService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {
    private final UserRepository userRepository;
    private final UserEditService userEditService;

    @Autowired
    public UserProfileController(UserRepository userRepository, UserEditService userEditService) {
        this.userRepository = userRepository;
        this.userEditService = userEditService;
    }

    @ModelAttribute("user")
    public User currentUser(Principal principal) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return userRepository.findByEmail(principal.getName());
    }

    @GetMapping
    public String userProfileHome(Principal principal, Model model) {
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
        return "panel/user/profile/userProfile";
    }

    @GetMapping("/edit")
    public String userEditProfileHome(@RequestParam("id") Long id,
                                      Model model) {
        model.addAttribute("userEdit", new UserEditDto(userRepository.getOne(id)));
        return "panel/user/profile/userProfileEdit";
    }

    @PostMapping("/edit")
    public String userEditProfileProceed(@ModelAttribute("userEdit") @Valid UserEditDto user,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/user/profile?failed";
        }
        userEditService.userDataEdit(user);
        return "redirect:/user/profile?success";
    }
}
