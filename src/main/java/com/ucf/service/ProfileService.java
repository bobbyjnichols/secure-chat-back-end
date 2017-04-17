package com.ucf.service;

import com.ucf.entity.User;
import org.springframework.stereotype.Service;

/*
* Service classes provide interfaces for implementation beans.
* */

@Service
public interface ProfileService {
    User updateProfile(User user);
}
