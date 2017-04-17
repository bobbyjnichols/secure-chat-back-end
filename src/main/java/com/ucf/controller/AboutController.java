package com.ucf.controller;

import com.ucf.dto.AboutDTO;
import com.ucf.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
* Controllers define endpoints that are available to accept RESTful requests and
* route them to the appropriate services.
*
* The about controller provides an endpoint to return basic version information
* about the application.
* This endpoint does not require authentication.
* */

@RestController
@RequestMapping(value = "/about")
public class AboutController {

    static final Logger logger = Logger.getLogger(AboutController.class);

    @Autowired
    private Response response;

    @Value("${app.version}")
    private String version;

    @Value("${app.name}")
    private String name;

    @Value("${app.description}")
    private String description;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAbout() {

        AboutDTO about = new AboutDTO(name, 'v' + version, description);

        return response.respond(about);
    }
}
