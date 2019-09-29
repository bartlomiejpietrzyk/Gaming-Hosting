package pl.bartlomiejpietrzyk.account;

import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.UserRepository;

public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
