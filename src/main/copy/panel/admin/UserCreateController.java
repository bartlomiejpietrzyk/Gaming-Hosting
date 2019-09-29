package pl.bartlomiejpietrzyk.panel.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartlomiejpietrzyk.registration.UserRegistrationService;
import pl.bartlomiejpietrzyk.user.User;
import pl.bartlomiejpietrzyk.user.dto.UserCreateDto;

import javax.validation.Valid;
import java.security.Principal;

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
        userDto.setLocked(false);
        userDto.setEnable(true);
        userRegistrationService.save(userDto);
        return "redirect:/api/admin/user/addUser?success";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal) {
        if (principal == null) {
            return "";
        }
        return principal.getName();
    }

}
