package pl.bartlomiejpietrzyk.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.GamingHostingApplication;
import pl.bartlomiejpietrzyk.email.EmailService;
import pl.bartlomiejpietrzyk.entity.PasswordToken;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.PasswordTokenRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ChangePasswordServiceImpl implements IChangePasswordService {
    private final Logger LOG = LoggerFactory.getLogger(LostPasswordServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public ChangePasswordServiceImpl(UserRepository userRepository,
                                     PasswordTokenRepository passwordTokenRepository,
                                     PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Boolean changePasswordSteps(User user) {
        PasswordToken pT = createPasswordToken(user);
        PasswordToken passwordToken = passwordTokenRepository.save(pT);
        SimpleMailMessage mailMessage = constructChangeTokenEmail(passwordToken.getToken(), user);
        Boolean aBoolean = emailService.sendMessage(String.valueOf(mailMessage.getTo()), mailMessage.getSubject(), mailMessage.getText());
        return aBoolean;
    }

    @Override
    public SimpleMailMessage constructChangeTokenEmail(String token, User user) {
        String url = new StringBuffer()
                .append(GamingHostingApplication.URL)
                .append("user/change?id=").append(user.getId())
                .append("&token=").append(token).toString();
        String url1 = new StringBuffer()
                .append(GamingHostingApplication.URL)
                .append("user/change?id=").append(user.getId())
                .append("&token=")
                .append(token)
                .append("&invalidate=1")
                .toString();
        String message = new StringBuffer()
                .append("Witaj ")
                .append(user.getFirstName())
                .append("!\nBy zmienic hasło kliknij w poniższy link!\n")
                .append(url)
                .append("!\nTo nie Ty? Kliknij w ten link żeby anulować zmianę hasła!\n")
                .append(url1)
                .toString();
        LOG.info(new StringBuilder()
                .append(LocalDateTime.now().format(GamingHostingApplication.FORMATTER))
                .append(" :: User Account:: ")
                .append("[ID: ").append(user.getId())
                .append(", Username: ").append(user.getUsername())
                .append(", Mail: ").append(user.getEmail())
                .append("] change password message sent!").toString());
        return prepareEmail(user.getEmail(), "Change Password", message + " \r\n" + url + "\n" + url1);

    }

    @Override
    public SimpleMailMessage prepareEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        return mailMessage;
    }

    @Override
    public PasswordToken createPasswordToken(User user) {
        PasswordToken passwordToken = new PasswordToken();
        passwordToken.setUser(user);
        passwordToken.setToken(UUID.randomUUID().toString());
        PasswordToken savedToken = passwordTokenRepository.save(passwordToken);
        return savedToken;

    }

    @Override
    public Integer validatePasswordToken(Long id, String token) {
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);

        if (passwordToken == null || (passwordToken.getUser().getId() != id)) {
            LOG.error(new StringBuilder()
                    .append(LocalDateTime.now().format(GamingHostingApplication.FORMATTER))
                    .append(" :: Change Password:: error occurred! Wrong token!").toString());
            return 0;
        }
        return 1;
    }

    @Override
    public void invalidatePasswordToken(String token) {
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
        passwordToken.setTokenUsed(LocalDateTime.now());
        passwordTokenRepository.save(passwordToken);
        LOG.info(new StringBuilder()
                .append(LocalDateTime.now().format(GamingHostingApplication.FORMATTER))
                .append(" :: Token Invalidate :: Token ID: ")
                .append(passwordToken.getToken())
                .append(" invalidated!").toString());
    }

    @Override
    public void changePassword(Long id, String password, String token) {
        User existing = userRepository.getOne(id);
        existing.setPassword(passwordEncoder.encode(password));
        userRepository.save(existing);
        invalidatePasswordToken(token);
        LOG.info(new StringBuilder()
                .append(LocalDateTime.now().format(GamingHostingApplication.FORMATTER))
                .append(" :: Change Password:: User ")
                .append(existing.getEmail())
                .append(" successfully changed password!").toString());
    }


}
