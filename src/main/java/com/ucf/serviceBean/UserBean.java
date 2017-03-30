package com.ucf.serviceBean;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBean implements UserService {
    private static final Logger logger = Logger.getLogger(UserBean.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepository.getUserByPhone(phone);
    }

    @Override
    public List<User> getAll() {
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }

    @Override
    public User getCurrentUser() throws UnauthorizedUserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken)
            throw new UnauthorizedUserException("User has not been authenticated.");

        return userRepository.getUserByPhone(
                ((User) authentication.getPrincipal())
                        .getPhone()
        );
    }
}
