package com.ucf.dto;

import com.ucf.entity.User;

/*
* Data Transfer Objects (or DTOs) define what from an object should be returned as JSON as a
* response to a request. These classes define the what information is accessible to users and
* acts as a layer of security as well as a way to increase response performance.
*
* The user DTO defines the properties available when querying for a user object.
* A notable exception is the user's password.
* */

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
