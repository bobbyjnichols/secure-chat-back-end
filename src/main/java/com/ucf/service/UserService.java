package com.ucf.service;

import com.ucf.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Service classes provide interfaces for implementation beans.
* */

@Service
public interface UserService {

    User getUserByUsername(String username);

    List<User> getAll();

    User getCurrentUser();

    User getUserByPhone(String phone);
}
