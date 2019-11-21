package pl.bartlomiejpietrzyk.controller.admin.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @ModelAttribute("newGame")
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
    public String createGameHome(@ModelAttribute("newGame") GameDto gameDto,
                                 Model model) {
        model.addAttribute("gameProceed", gameDto);
        return "panel/adminGameAddGame";
    }

    @PostMapping("/add")
    public String createGameProceed(@ModelAttribute("gameProceed") GameDto gameDto) {
        boolean b = gameService.addGame(gameDto);
        if (!b) {
            return "redirect:/add?failed";
        }

        return "redirect:/admin/game/details?id=" + gameService.getIdByTitle(gameDto.getTitle());
    }

    @GetMapping("/details")
    public String showGameDetailsHome(@RequestParam("id") Long id,
                                      Model model) {
        GameDto gameDetails = gameService.getGameDetails(id);
        model.addAttribute("game", gameDetails);
        return "panel/adminGameDetails";
    }

    @GetMapping("/edit")
    public String editGameDetailsHome(@RequestParam("id") Long id,
                                      Model model) {
        GameDto gameDetails = gameService.getGameDetails(id);
        model.addAttribute("editGame", gameDetails);
        return "panel/adminGameDetails";
    }

    @PostMapping("/edit")
    public String editGameDetailsProceed(@RequestParam("id") Long id,
                                         @ModelAttribute("editGame") GameDto gameDto) {
        GameDto gameDto1 = gameService.editGame(id, gameDto);
        return "panel/details?id=" + id + "?success";
    }
}
