package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.validator.FieldMatch;
import pl.bartlomiejpietrzyk.validator.ValidPassword;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match"),
})
public class UserChangePasswordDto {

    @NotEmpty
    private String id;
    @NotEmpty
    @ValidPassword
    private String password;
    @Length(min = 8, max = 32)
    @NotEmpty
    private String passwordConfirm;
    @NotEmpty
    private String token;


    @Autowired
    public UserChangePasswordDto() {
    }

    public UserChangePasswordDto(User that) {
        this.id = String.valueOf(that.getId());
        this.password = that.getPassword();
        this.token = getToken();
    }
}
