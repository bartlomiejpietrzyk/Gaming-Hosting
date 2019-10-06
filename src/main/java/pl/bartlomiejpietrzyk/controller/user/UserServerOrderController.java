package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserServerOrderController {

    public String userServerOrderHome() {
        return "user/serverOrder";
    }


}
