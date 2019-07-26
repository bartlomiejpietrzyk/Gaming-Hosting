package com.github.bartlomiejpietrzyk.registration;

import com.github.bartlomiejpietrzyk.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRegistrationService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}