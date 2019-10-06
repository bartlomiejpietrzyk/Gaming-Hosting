package pl.bartlomiejpietrzyk.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.GamingHostingApplication;
import pl.bartlomiejpietrzyk.entity.PasswordToken;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.PasswordTokenRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Service
@Transactional
public class LostPasswordServiceImpl implements LostPasswordService {
    private final Logger LOG = LoggerFactory.getLogger(LostPasswordServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LostPasswordServiceImpl(UserRepository userRepository,
                                   PasswordTokenRepository passwordTokenRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SimpleMailMessage constructResetTokenEmail(String token, User user) {
        String url = new StringBuffer()
                .append(GamingHostingApplication.URL)
                .append("/account/reset?id=").append(user.getId())
                .append("&token=").append(token).toString();

        String message = new StringBuffer()
                .append("Witaj ")
                .append(user.getFirstName())
                .append("!\nBy zresetować hasło kliknij w poniższy link!\n").toString();
        LOG.info(String.format("%s :: User Account:: [ID: %s, Username: %s, Mail: %s] lost password message sent!")
                , LocalDateTime.now().format(GamingHostingApplication.formatter),
                user.getUsername(), user.getEmail());
        return constructEmail(user, "Reset Password", message + " \r\n" + url);
    }

    private SimpleMailMessage constructEmail(User user, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        return mailMessage;
    }

    @Override
    public PasswordToken createPasswordToken(User user) {
        PasswordToken token = new PasswordToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        PasswordToken savedToken = passwordTokenRepository.save(token);
        LOG.info(String.format("%s :: User Account:: [ID: %s, Username: %s, Mail: %s] created password reset token!")
                , LocalDateTime.now().format(GamingHostingApplication.formatter),
                user.getUsername(), user.getEmail());
        return savedToken;
    }

    @Override
    public Integer validatePasswordToken(Long id, String token) {
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);

        if (passwordToken == null || (passwordToken.getUser().getId() != id)) {
            LOG.error(String.format("%s :: Lost Password :: error occured! Wrong token!")
                    , LocalDateTime.now().format(GamingHostingApplication.formatter));
            return 0;
        }

        User user = passwordToken.getUser();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null,
                        Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_AUTH")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOG.info(String.format("%s :: User Account:: [ID: %s, Username: %s, Mail: %s] lost password message sent!")
                , LocalDateTime.now().format(GamingHostingApplication.formatter),
                user.getUsername(), user.getEmail());

        return null;
    }

    @Override
    public void invalidatePasswordToken(String token) {
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
        passwordToken.setTokenUsed(LocalDateTime.now());
        passwordTokenRepository.save(passwordToken);
        LOG.info(String.format("%s :: Token Invalidate :: Token ID: %s invalidated!")
                , LocalDateTime.now().format(GamingHostingApplication.formatter), passwordToken.getId());
    }

    @Override
    public void changePassword(String id, String password, String token) {
        User existing = userRepository.getOne(Long.valueOf(id));
        existing.setPassword(passwordEncoder.encode(password));
        userRepository.save(existing);
        invalidatePasswordToken(token);
        LOG.info(String.format("%s :: Reset Password :: User '%s' successfully reseted password!")
                , LocalDateTime.now().format(GamingHostingApplication.formatter), existing.getEmail());

    }
}
