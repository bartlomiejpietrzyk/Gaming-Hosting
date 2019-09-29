package pl.bartlomiejpietrzyk.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Override
    Game getOne(Long aLong);

    List<Game> findAll();

    <S extends Game> S save(S s);

    boolean exists(Long id);

    long count();

    void delete(Long id);


}
