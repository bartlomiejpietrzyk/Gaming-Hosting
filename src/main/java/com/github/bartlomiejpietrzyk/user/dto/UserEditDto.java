package com.github.bartlomiejpietrzyk.user.dto;

import com.github.bartlomiejpietrzyk.user.Role;
import com.github.bartlomiejpietrzyk.user.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserEditDto {
    @Autowired
    public UserEditDto() {
    }

    public UserEditDto(User that) {
        this.id = String.valueOf(that.getId());
        this.firstName = that.getFirstName();
        this.lastName = that.getLastName();
        this.email = that.getEmail();
        this.mobile = String.valueOf(that.getMobile());
        this.address = that.getAddress();
        this.postCode = that.getPostCode();
        this.city = that.getCity();
        this.roles = that.getRoles().stream().map(Role::toString).collect(Collectors.toList());
        this.locked = that.getLocked();
        this.enable = that.getEnable();
    }

    private String id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @NotEmpty
    private String mobile;

    @NotEmpty
    private String address;

    @NotEmpty
    private String postCode;

    @NotEmpty
    private String city;

    @NotEmpty
    private Collection<String> roles;

    @Column(nullable = false)
    private Boolean enable;

    @Column(nullable = false)
    private Boolean locked;

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

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
