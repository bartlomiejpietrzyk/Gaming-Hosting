package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Mail {
    @Email
    @NotNull
    @Size(min = 4, message = "Please, set an email address to send the message to it")
    private String to;
    private String subject;
    private String text;
}
