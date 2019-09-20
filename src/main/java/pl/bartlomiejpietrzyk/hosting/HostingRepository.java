package pl.bartlomiejpietrzyk.hosting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartlomiejpietrzyk.game.Game;

import java.util.List;

@Repository
public interface HostingRepository extends JpaRepository<Hosting, Long> {

    @Override
    Hosting getOne(Long aLong);

    List<Hosting> findAll();

    <S extends Game> S save(S s);

    boolean exists(Long id);

    void delete(Long id);

    long count();
}
