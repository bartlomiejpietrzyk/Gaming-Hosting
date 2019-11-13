package pl.bartlomiejpietrzyk.game;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartlomiejpietrzyk.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
