package pl.bartlomiejpietrzyk.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    public String homePage() {
        return "index";
    }
}
