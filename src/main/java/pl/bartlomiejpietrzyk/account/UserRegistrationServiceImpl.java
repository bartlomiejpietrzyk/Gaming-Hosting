package pl.bartlomiejpietrzyk.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final Logger LOG = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);
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
        LOG.info(String.format("%s :: User Account:: [ID: %s, Username: %s, Mail: %s has been created!")
                , LocalDateTime.now().format(GamingHostingApplication.FORMATTER),
                save.getUsername(), save.getEmail());
    }

    @Override
    public void unlockAccount(String uuid) {
        User user = userRepository.findOneByUuid(uuid);
        user.setEnabled(true);
        userRepository.save(user);
        LOG.info(String.format("%s :: User Account:: [ID: %s, Username: %s, Mail: %s] has been activated!")
                , LocalDateTime.now().format(GamingHostingApplication.FORMATTER),
                user.getUsername(), user.getEmail());
    }
}
