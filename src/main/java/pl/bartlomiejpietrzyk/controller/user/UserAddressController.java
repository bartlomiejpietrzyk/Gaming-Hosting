package pl.bartlomiejpietrzyk.controller.user;

import org.springframework.stereotype.Controller;

@Controller("/user/address")
public class UserAddressController {

    public String userAddressPage() {
        return "user/userAddress";
    }
}
