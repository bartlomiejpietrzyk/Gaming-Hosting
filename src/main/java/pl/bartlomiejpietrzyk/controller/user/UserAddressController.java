package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/user")
public class UserAddressController {
    @GetMapping("/address")
    public String userAddressPage() {
        return "user/userAddress";
    }
}
