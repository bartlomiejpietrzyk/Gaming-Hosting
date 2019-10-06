package pl.bartlomiejpietrzyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bartlomiejpietrzyk.entity.User;

@Setter
@Getter
public class UserEditDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private String street;
    private Integer building;
    private Integer home;
    private String city;
    private String postCode;

    @Autowired
    public UserEditDto() {
    }

    public UserEditDto(User that) {
        this.username = that.getUsername();
        this.email = that.getEmail();
        this.firstName = that.getFirstName();
        this.lastName = that.getLastName();
        this.mobile = that.getMobile();
        this.street = that.getAddress().getStreet();
        this.building = that.getAddress().getBuilding();
        this.home = that.getAddress().getHomeNumber();
        this.city = that.getAddress().getCity();
        this.postCode = that.getAddress().getPostCode();
    }
}
