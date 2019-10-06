package pl.bartlomiejpietrzyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartlomiejpietrzyk.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
