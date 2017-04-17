package com.ucf.dto;

/*
* Data Transfer Objects (or DTOs) define what from an object should be returned as JSON as a
* response to a request. These classes define the what information is accessible to users and
* acts as a layer of security as well as a way to increase response performance.
*
* The About DTO defines the information returned in a /about request to the server.
* */

public class AboutDTO {

    private final String name;
    private final String version;
    private final String description;

    public AboutDTO(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDescription() {
        return this.description;
    }
}
