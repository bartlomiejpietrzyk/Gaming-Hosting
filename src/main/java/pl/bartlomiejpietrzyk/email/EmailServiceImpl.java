package pl.bartlomiejpietrzyk.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    private JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(@Qualifier("getJavaMailSender")
                                    JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public Boolean sendMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            return true;
        } catch (MailException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}