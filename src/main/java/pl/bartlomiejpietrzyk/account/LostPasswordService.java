package pl.bartlomiejpietrzyk.account;

import org.springframework.mail.SimpleMailMessage;
import pl.bartlomiejpietrzyk.entity.User;

public interface LostPasswordService {
    SimpleMailMessage constructResetTokenEmail(String token, User user);

    void createPasswordToken(User user);

    String validatePasswordToken(Long id, String token);

    void invalidatePasswordToken(String token);

    void changePassword(String id, String password, String token);
}
