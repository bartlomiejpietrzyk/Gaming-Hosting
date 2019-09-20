package pl.bartlomiejpietrzyk.registration;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.bartlomiejpietrzyk.user.User;
import pl.bartlomiejpietrzyk.user.dto.UserCreateDto;
import pl.bartlomiejpietrzyk.user.dto.UserRegistrationDto;

public interface UserRegistrationService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    User save(UserCreateDto registration);
}