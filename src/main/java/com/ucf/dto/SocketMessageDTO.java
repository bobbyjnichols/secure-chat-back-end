package com.ucf.dto;

/*
* Data Transfer Objects (or DTOs) define what from an object should be returned as JSON as a
* response to a request. These classes define the what information is accessible to users and
* acts as a layer of security as well as a way to increase response performance.
*
* The Socket Message DTO defines the properties available when a socket message is sent from
* the server.
* */

public class SocketMessageDTO {

    private final String name;
    private final String event;
    private final String description;
    private final Integer uk;
    private final Object response;


    public SocketMessageDTO(String name, String event, String description, Integer uk, Object response) {
        this.name = name;
        this.event = event;
        this.description = description;
        this.uk = uk;
        this.response = response;
    }

    public String getName() {
        return this.name;
    }

    public String getEvent() {
        return this.event;
    }

    public Integer getUK() {
        return this.uk;
    }

    public String getDescription() {
        return this.description;
    }

    public Object getResponse() {
        return this.response;
    }

}
