package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    public String userHomePage() {
        return "panel/dashboard";
    }
}
