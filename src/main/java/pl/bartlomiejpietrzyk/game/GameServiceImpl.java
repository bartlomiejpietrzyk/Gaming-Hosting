package pl.bartlomiejpietrzyk.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.dto.GameDto;
import pl.bartlomiejpietrzyk.entity.Game;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements IGameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addGame(GameDto gameDto) {
        Game gameToSave = dtoToGame(gameDto);
        Game save = gameRepository.save(gameToSave);
        return save;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> all = gameRepository.findAll();
        return all;
    }

    @Override
    public Game editGame(Long id) {
        Game existing = gameRepository.getOne(id);
        return existing;
    }

    @Override
    public Boolean setAvailable(boolean available) {
        return null;
    }

    @Override
    public void removeGame(Long id) {

    }

    @Override
    public Game dtoToGame(GameDto gameDto) {
        Game game = new Game();
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setPublicSlotPrice(gameDto.getPublicSlotPrice());
        game.setPublicMaxSlot(gameDto.getPublicMaxSlot());
        game.setPublicMinSlot(gameDto.getPublicMinSlot());
        game.setPrivateSlotPrice(gameDto.getPrivateSlotPrice());
        game.setPrivateMaxSlot(gameDto.getPrivateMaxSlot());
        game.setPrivateMinSlot(gameDto.getPrivateMinSlot());
        return game;
    }
}
