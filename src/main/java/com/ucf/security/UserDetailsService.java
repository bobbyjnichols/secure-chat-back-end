package com.ucf.security;

import com.ucf.entity.User;
import com.ucf.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByPhone(username);

        this.validateCredentials(user);

        if(user == null)
            throw new UsernameNotFoundException("Username '" + username + "' does not have an account");

        return (UserDetails) user;
    }

    private void validateCredentials(User user) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if(user == null)
                logger.info("Username '" + username + "' does not have an account");
            else if (user.getPassword() == null)
                logger.error("User '" + username + "' has not created a password");
            else if(BCrypt.checkpw(password, user.getPassword()))
                logger.info(username + "' Login Success");
            else
                logger.info(username + "' Login Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
