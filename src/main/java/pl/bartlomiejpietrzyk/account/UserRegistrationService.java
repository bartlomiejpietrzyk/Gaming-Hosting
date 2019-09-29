package pl.bartlomiejpietrzyk.account;

import pl.bartlomiejpietrzyk.entity.User;

public interface UserRegistrationService {
    User findByEmail(String email);

    void saveUser(User user);
}
