package pl.bartlomiejpietrzyk.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    List<User> findAll();

    @Override
    User getOne(Long aLong);

    @Override
    void delete(Long id);

    @Override
    long count();

    @Override
    <S extends User> S save(S s);
}