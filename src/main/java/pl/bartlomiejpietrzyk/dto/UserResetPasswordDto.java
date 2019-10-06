package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.validator.FieldMatch;
import pl.bartlomiejpietrzyk.validator.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match"),
})
public class UserResetPasswordDto {
    @NotEmpty
    private String id;
    @Email
    @NotEmpty
    public String email;
    @Length(min = 8, max = 32)
    @NotEmpty
    @ValidPassword
    public String password;
    @Length(min = 8, max = 32)
    @NotEmpty
    public String passwordConfirm;
    @NotEmpty
    public String token;

    @Autowired
    public UserResetPasswordDto() {
    }

    public UserResetPasswordDto(User that) {
        this.id = String.valueOf(that.getId());
        this.email = that.getEmail();
        this.password = that.getPassword();
        this.token = getToken();
    }
}
