package com.ucf.controller;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;
import com.ucf.entity.User;
import com.ucf.repository.UserRepository;
import com.ucf.service.SignupService;
import com.ucf.service.TwillioService;
import com.ucf.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private TwillioService twillioService;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> signup(
            @RequestParam(name = "phone") String phoneInput,
            @RequestParam(name = "password") String password
    ) {
        String formattedPhone = twillioService.getPhoneNumber(phoneInput).toString();
        User user = userRepository.getUserByPhone(formattedPhone);
        if (user == null) {
            signup.signup(formattedPhone, password);
            return response.respond(HttpStatus.ACCEPTED);
        } else {
            return response.respond(HttpStatus.CONFLICT);
        }
    }
}
