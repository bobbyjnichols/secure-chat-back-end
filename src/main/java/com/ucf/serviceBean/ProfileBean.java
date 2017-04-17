package com.ucf.serviceBean;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The profile component offers a connection from the profile controller to associated repository.
* */

@Component
public class ProfileBean implements ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateProfile(User user) {
        return userRepository.save(user);
    }
}
