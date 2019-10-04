package pl.bartlomiejpietrzyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartlomiejpietrzyk.account.UserRegistrationService;
import pl.bartlomiejpietrzyk.dto.UserRegistrationDto;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

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
                                      BindingResult result) throws SQLIntegrityConstraintViolationException {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "redirect:/registration?notMatch";
        }
        if (userRegistrationService.findByEmail(userDto.getEmail()) != null) {
            return "redirect:/registration?emailExist";
        }

        if (result.hasErrors()) {
            return "redirect:/registration?error";
        }
        userRegistrationService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/account")
    public String activateAccount(@RequestParam("uuid") String uuid, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registration?notActive";
        }
        userRegistrationService.unlockAccount(uuid);
        return "redirect:/registration?active";
    }
}