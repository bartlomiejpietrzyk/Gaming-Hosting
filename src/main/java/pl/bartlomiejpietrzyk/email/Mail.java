package pl.bartlomiejpietrzyk.email;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Mail {
    @Email
    @NotNull
    @Size(min = 4, message = "Please, set an email address to send the message to it")
    private String to;
    private String subject;
    private String text;
}
