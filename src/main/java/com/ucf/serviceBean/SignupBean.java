package com.ucf.serviceBean;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The signup component facilitates in the creation of a new user and assigning
* values to that user.
* */

@Component
public class SignupBean implements SignupService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void signup(String phone, String password) {
        User user = new User();
        user.setPhone(phone).setPassword(password);
        userRepository.save(user);
    }
}
