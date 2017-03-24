package com.ucf.service;

import com.ucf.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robert Nichols
 * @category Services
 */
@Service
public interface UserService {

    User getUserByUsername(String username);

    List<User> getAll();

    User getCurrentUser();
}
