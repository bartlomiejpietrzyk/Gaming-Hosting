package pl.bartlomiejpietrzyk.email;

public interface EmailService {
    Boolean sendMessage(String to,
                        String subject,
                        String text);
}
