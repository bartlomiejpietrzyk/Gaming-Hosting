package pl.bartlomiejpietrzyk.user.dto;

import pl.bartlomiejpietrzyk.user.User;

public class UserListDto {
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String confirmEmail;
    private String mobile;
    private String address;
    private String postCode;
    private String city;

    public UserListDto() {
    }

    public UserListDto(User that) {
        this.id = String.valueOf(that.getId());
        this.firstName = that.getFirstName();
        this.lastName = that.getLastName();
        this.email = that.getEmail();
        this.mobile = String.valueOf(that.getMobile());
        this.address = that.getAddress();
        this.postCode = that.getPostCode();
        this.city = that.getCity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
