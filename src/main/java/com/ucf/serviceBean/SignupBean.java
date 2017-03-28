package com.ucf.serviceBean;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
