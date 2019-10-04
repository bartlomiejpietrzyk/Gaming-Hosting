package pl.bartlomiejpietrzyk.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.GamingHostingApplication;
import pl.bartlomiejpietrzyk.dto.UserRegistrationDto;
import pl.bartlomiejpietrzyk.email.EmailService;
import pl.bartlomiejpietrzyk.entity.Role;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.RoleRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserRegistrationServiceImpl(UserRepository userRepository,
                                       RoleRepository roleRepository,
                                       BCryptPasswordEncoder passwordEncoder,
                                       EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserRegistrationDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLocked(false);
        user.setEnabled(false);
        user.setEmail(userDto.getEmail());
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        User save = userRepository.save(user);
        emailService.sendMessage(save.getEmail(),
                String.format("Hello %s! Account created!",
                        save.getUsername()),
                "To activate proceed: " + GamingHostingApplication.URL
                        + "account?uuid=" + save.getUuid());
    }

    @Override
    public void unlockAccount(String uuid) {
        User oneByUuid = userRepository.findOneByUuid(uuid);
        oneByUuid.setEnabled(true);
        userRepository.save(oneByUuid);
    }
}
