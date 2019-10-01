package pl.bartlomiejpietrzyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartlomiejpietrzyk.account.UserRegistrationService;
import pl.bartlomiejpietrzyk.dto.UserRegistrationDto;
import pl.bartlomiejpietrzyk.entity.User;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {

        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        User existingEmail = userRegistrationService.findByEmail(userDto.getEmail());
        if (existingEmail != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }
        userDto.setLocked(false);
        userDto.setEnabled(true);
        userRegistrationService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/account")
    public String activateAccount(@RequestParam("uuid") String uuid) {
        userRegistrationService.unlockAccount(uuid);
        return "redirect:/registration?active";
    }
}