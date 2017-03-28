package com.ucf.controller;

import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.SignupService;
import com.ucf.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/signup")
public class SignupController {
    static final Logger logger = Logger.getLogger(SignupController.class);

    @Autowired
    private SignupService signup;

    @Autowired
    private Response response;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> signup(
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "password") String password
    ) {
        User user = userRepository.getUserByPhone(phone);

        if (user == null) {
            signup.signup(phone, password);
            return response.respond(HttpStatus.ACCEPTED);
        } else {
            return response.respond(HttpStatus.CONFLICT);
        }
    }
}
