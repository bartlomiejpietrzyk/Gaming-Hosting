package com.github.bartlomiejpietrzyk.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
    User getOne(Long aLong);

    void delete(Long id);
    <S extends User> S save(S s);
//    <S extends User> S save(S s);
}