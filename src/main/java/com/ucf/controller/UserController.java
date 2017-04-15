package com.ucf.controller;

import com.ucf.dto.UserDTO;
import com.ucf.entity.User;
import com.ucf.service.ProfileService;
import com.ucf.service.UserService;
import com.ucf.util.ListUtils;
import com.ucf.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private Response response;

    @Autowired
    private UserService userService;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        return response.respond(ListUtils.parallelTransform(userService.getAll(), UserDTO::new));
    }
}
