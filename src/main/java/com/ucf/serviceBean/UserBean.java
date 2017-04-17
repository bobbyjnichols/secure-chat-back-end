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

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The user component provides a means to retrieve a user object via various identification
* methods. Note that the user service is able to return the applicable user in a specific
* application context. Essentially the user can be identified with nothing other than the
* access token they used to request content.
* */

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
