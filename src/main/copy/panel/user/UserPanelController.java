package pl.bartlomiejpietrzyk.panel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartlomiejpietrzyk.user.UserService;
import pl.bartlomiejpietrzyk.user.dto.UserEditDto;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/api/user")
public class UserPanelController {
    private UserService userService;

    @Autowired
    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    //todo make sites for servers n payments


    @GetMapping("/myProfile")
    public String showUserProfile() {
        return "panel/userProfile";
    }

    @GetMapping("/edit")
    public String showEditForm() {
        return "panel/userProfileEdit";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute("userAccount") @Valid UserEditDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/api/user/edit?id=" + userDto.getId();
        }
        userService.userEditUpdate(userDto);
        return "redirect:/api/user/edit?id=" + userDto.getId();
    }

    @GetMapping("/myServers")
    public String showUserServers() {
        return "panel/userServersList";
    }

    @GetMapping("/myPayments")
    public String showUserPayments() {
        return "panel/userPaymentsList";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal, Model model) {
        model.addAttribute("userAccount", userService.findUserByEmail(principal.getName()));
        return principal.getName();
    }

}
