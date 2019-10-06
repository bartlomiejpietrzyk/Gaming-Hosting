package pl.bartlomiejpietrzyk.account;

import org.springframework.mail.SimpleMailMessage;
import pl.bartlomiejpietrzyk.entity.PasswordToken;
import pl.bartlomiejpietrzyk.entity.User;

public interface LostPasswordService {
    SimpleMailMessage constructResetTokenEmail(String token, User user);

    PasswordToken createPasswordToken(User user);

    Integer validatePasswordToken(Long id, String token);

    void invalidatePasswordToken(String token);

    void changePassword(String id, String password, String token);
}
