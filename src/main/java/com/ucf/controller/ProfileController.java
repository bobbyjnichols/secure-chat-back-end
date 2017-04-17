package com.ucf.controller;

import com.ucf.dto.UserDTO;
import com.ucf.entity.User;
import com.ucf.service.ProfileService;
import com.ucf.service.UserService;
import com.ucf.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
* Controllers define endpoints that are available to accept RESTful requests and
* route them to the appropriate services.
*
* The profile controller provides endpoints to return and modify a user's profile.
* This endpoint requires authentication.
* */

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private Response response;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateProfile(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "avatar", required = false) String avatar
    ) {
        User user = userService.getCurrentUser();
        user.setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAvatarURL(avatar);

        profileService.updateProfile(user);
        return response.respond(HttpStatus.ACCEPTED);
    }

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getProfile() {
        return response.respond(new UserDTO(userService.getCurrentUser()));
    }
}
