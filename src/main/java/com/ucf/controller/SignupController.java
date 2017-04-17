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

/*
* Controllers define endpoints that are available to accept RESTful requests and
* route them to the appropriate services.
*
* The signup controller provides an endpoint to register a new user.
* This endpoint does not require authentication.
* */

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
        String formattedPhone;
        try {
            formattedPhone = twillioService.getPhoneNumber(phoneInput).getPhoneNumber().toString();
        } catch(com.twilio.exception.ApiException e) {
            return response.respond(HttpStatus.BAD_REQUEST, "The phone number provided is not valid");
        }

        User user = userRepository.getUserByPhone(formattedPhone);
        if (user == null) {
            signup.signup(formattedPhone, password);
            return response.respond(HttpStatus.ACCEPTED);
        } else {
            return response.respond(HttpStatus.CONFLICT, "A user account already exists for the number provided");
        }
    }
}
