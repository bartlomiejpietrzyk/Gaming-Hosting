package pl.bartlomiejpietrzyk.account;

import pl.bartlomiejpietrzyk.dto.UserRegistrationDto;
import pl.bartlomiejpietrzyk.entity.User;

public interface IUserRegistrationService {
    User findByEmail(String email);

    void saveUser(UserRegistrationDto user);

    void unlockAccount(String uuid);
}
