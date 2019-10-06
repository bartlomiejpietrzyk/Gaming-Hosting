package pl.bartlomiejpietrzyk.user.dto;

import pl.bartlomiejpietrzyk.user.User;

public class UserDetailsDto {
    private String id;
    private String uuid;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private String address;
    private String postCode;
    private String city;
    //    private Collection<ServerDetailsDto> serversSet;
    private String payments;
    private String created;
    private String locked;
    private String enable;
    private String roles;

    public UserDetailsDto() {
    }

    public UserDetailsDto(User that) {
//        this.uuid = that.getUuid();
        this.id = String.valueOf(that.getId());
        this.email = that.getEmail();
        this.password = that.getPassword();
        this.firstName = that.getLastName();
        this.lastName = that.getLastName();
        this.mobile = String.valueOf(that.getMobile());
        this.address = that.getAddress();
        this.postCode = that.getPostCode();
        this.city = that.getCity();
        this.created = String.valueOf(that.getCreated());
        this.payments = String.valueOf(that.getPayments());
        this.locked = String.valueOf(that.getLocked());
        this.enable = String.valueOf(that.getEnable());
        this.roles = String.valueOf(that.getRoles());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

//    public Collection<ServerDetailsDto> getServersSet() {
//        return serversSet;
//    }
//
//    public void setServersSet(Collection<ServerDetailsDto> serversSet) {
//        this.serversSet = serversSet;
//    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

