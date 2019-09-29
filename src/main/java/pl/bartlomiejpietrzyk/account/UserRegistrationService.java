package pl.bartlomiejpietrzyk.account;

import pl.bartlomiejpietrzyk.dto.UserRegistrationDto;
import pl.bartlomiejpietrzyk.entity.User;

public interface UserRegistrationService {
    User findByEmail(String email);

    void saveUser(UserRegistrationDto user);
}
