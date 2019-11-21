package pl.bartlomiejpietrzyk.game;

import pl.bartlomiejpietrzyk.dto.GameDto;
import pl.bartlomiejpietrzyk.entity.Game;

import java.util.List;

public interface IGameService {

    boolean addGame(GameDto gameDto);

    GameDto getGameDetails(Long id);

    List<Game> getAllGames();

    GameDto editGame(Long id, GameDto gameDto);

    Boolean setAvailable(boolean available);

    void removeGame(Long id);

    Long getIdByTitle(String title);
}
