package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserServersHome {

    public String userServerHome() {
        return "user/servers";
    }
}
