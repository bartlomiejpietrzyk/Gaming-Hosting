package pl.bartlomiejpietrzyk.controller.admin.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartlomiejpietrzyk.dto.GameDto;
import pl.bartlomiejpietrzyk.entity.Game;
import pl.bartlomiejpietrzyk.game.IGameService;

import java.util.List;

@Controller
@RequestMapping("/admin/game")
public class GameController {
    private final IGameService gameService;

    @Autowired
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @ModelAttribute("game")
    public GameDto gameModel() {
        return new GameDto();
    }

    @GetMapping
    public String gamesList(Model model) {
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("gamesList", allGames);
        return "panel/adminGameList";
    }

    @GetMapping("/add")
    public String createGameHome(@ModelAttribute("game") GameDto gameDto,
                                 Model model) {
        model.addAttribute("gameProceed", gameDto);
        return "panel/adminGameAddGame";
    }

    @PostMapping("/add")
    public String createGameProceed(@ModelAttribute("gameProceed") GameDto gameDto) {
        Game game = gameService.addGame(gameDto);
        return "redirect:/admin/game";
    }
}
