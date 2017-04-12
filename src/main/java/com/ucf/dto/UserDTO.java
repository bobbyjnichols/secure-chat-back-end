package com.ucf.dto;

import com.ucf.entity.User;

public class UserDTO {

    private Integer key;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public UserDTO(User user) {
        this.key = user.getKey();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public Integer getKey() {
        return key;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
