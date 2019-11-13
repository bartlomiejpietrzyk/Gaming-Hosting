package pl.bartlomiejpietrzyk.game;

import pl.bartlomiejpietrzyk.dto.GameDto;
import pl.bartlomiejpietrzyk.entity.Game;

import java.util.List;

public interface IGameService {

    Game addGame(GameDto gameDto);

    List<Game> getAllGames();

    Game editGame(Long id);

    Boolean setAvailable(boolean available);

    void removeGame(Long id);

    Game dtoToGame(GameDto gameDto);
}
