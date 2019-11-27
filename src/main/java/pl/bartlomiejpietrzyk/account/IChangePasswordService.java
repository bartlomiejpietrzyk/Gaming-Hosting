package pl.bartlomiejpietrzyk.account;

import org.springframework.mail.SimpleMailMessage;
import pl.bartlomiejpietrzyk.entity.PasswordToken;
import pl.bartlomiejpietrzyk.entity.User;

public interface IChangePasswordService {
    Boolean changePasswordSteps(User user);

    SimpleMailMessage constructChangeTokenEmail(String token, User user);

    SimpleMailMessage prepareEmail(String to, String subject, String text);

    PasswordToken createPasswordToken(User user);

    Integer validatePasswordToken(Long id, String token);

    void invalidatePasswordToken(String token);

    void changePassword(Long id, String password, String token);
}
