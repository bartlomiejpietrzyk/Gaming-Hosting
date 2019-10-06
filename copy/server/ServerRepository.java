package pl.bartlomiejpietrzyk.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Override
    Server getOne(Long aLong);

    List<Server> findAll();

    Server save(ServerOrderDto orderDto);

    <S extends Server> S save(S s);

    boolean exists(Long id);

    long count();

    void delete(Long id);
}
