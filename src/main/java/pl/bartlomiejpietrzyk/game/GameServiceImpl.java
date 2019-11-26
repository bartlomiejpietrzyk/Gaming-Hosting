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
    public boolean addGame(GameDto gameDto) {
        Game gameToSave = dtoToGame(gameDto);
        Game save = gameRepository.save(gameToSave);
        Game one = gameRepository.getOne(save.getId());

        return one.equals(save);
    }

    @Override
    public GameDto getGameDetails(Long id) {
        Game one = gameRepository.getOne(id);
        return new GameDto(one);
    }

    @Override
    public Long getIdByTitle(String name) {
        return gameRepository.findByTitle(name).getId();
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> all = gameRepository.findAll();
        return all;
    }

    @Override
    public void editGame(Long id, GameDto gameDto) {
        Game game = gameRepository.getOne(id);
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setPublicSlotPrice(gameDto.getPublicSlotPrice());
        game.setPublicMaxSlot(gameDto.getPublicMaxSlot());
        game.setPublicMinSlot(gameDto.getPublicMinSlot());
        game.setPrivateSlotPrice(gameDto.getPrivateSlotPrice());
        game.setPrivateMaxSlot(gameDto.getPrivateMaxSlot());
        game.setPrivateMinSlot(gameDto.getPrivateMinSlot());
        game.setAvailable(gameDto.getAvailable());
        game.setLiveStreamTvSlot(gameDto.getLiveStreamTvSlot());
        game.setLiveStreamTvSlotPrice(gameDto.getLiveStreamTvSlotPrice());
        gameRepository.saveAndFlush(game);
    }

    @Override
    public Boolean setAvailable(boolean available) {
        return null;
    }

    @Override
    public void removeGame(Long id) {
        gameRepository.deleteById(id);

    }

    private Game dtoToGame(GameDto gameDto) {
        Game game = new Game();
        game.setId(gameDto.getId());
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setPublicSlotPrice(gameDto.getPublicSlotPrice());
        game.setPublicMaxSlot(gameDto.getPublicMaxSlot());
        game.setPublicMinSlot(gameDto.getPublicMinSlot());
        game.setPrivateSlotPrice(gameDto.getPrivateSlotPrice());
        game.setPrivateMaxSlot(gameDto.getPrivateMaxSlot());
        game.setPrivateMinSlot(gameDto.getPrivateMinSlot());
        game.setAvailable(gameDto.getAvailable());
        game.setLiveStreamTvSlot(gameDto.getLiveStreamTvSlot());
        game.setLiveStreamTvSlotPrice(gameDto.getLiveStreamTvSlotPrice());
        return game;
    }
}
