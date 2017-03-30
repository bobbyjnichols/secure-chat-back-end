package com.ucf.serviceBean;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileBean implements ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateProfile(User user) {
        return userRepository.save(user);
    }
}
