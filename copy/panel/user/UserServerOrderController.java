package pl.bartlomiejpietrzyk.panel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartlomiejpietrzyk.game.Game;
import pl.bartlomiejpietrzyk.game.GameRepository;
import pl.bartlomiejpietrzyk.server.ServerOrderDto;
import pl.bartlomiejpietrzyk.server.ServerService;
import pl.bartlomiejpietrzyk.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/user/server")
public class UserServerOrderController {

    private ServerService serverService;
    private UserService userService;
    private GameRepository gameRepository;

    @Autowired
    public UserServerOrderController(ServerService serverService, UserService userService, GameRepository gameRepository) {
        this.serverService = serverService;
        this.userService = userService;
        this.gameRepository = gameRepository;
    }


    @ModelAttribute("server")
    public ServerOrderDto serverOrderDto() {
        return new ServerOrderDto();
    }

    @GetMapping("/order")
    public String showServerOrderForm(Model model) {
        List<Game> all = gameRepository.findAll();
        //todo gra -> load -> max slot -> select -> save
        //todo zrobic wyswietlanie liczby slotow zalezne od gry
        //todo zrobic rent start = date i expire najpierw na miesiace
        return "panel/userOrderServer";
    }

    @PostMapping("/order")
    public String serverOrder(@ModelAttribute("server") @Valid ServerOrderDto orderDto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "panel/userOrderServer";
        }
        serverService.orderServer(orderDto);
        return "redirect:/api/user/server?success";
    }

    @ModelAttribute("loggedIn")
    public String currentUserName(Principal principal, Model model) {
        model.addAttribute("userAccount", userService.findUserByEmail(principal.getName()));
        return principal.getName();
    }

    @ModelAttribute("game")
    public List<Game> allGamesList() {
        return gameRepository.findAll();
    }

}
