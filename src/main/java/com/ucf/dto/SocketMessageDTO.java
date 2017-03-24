package com.ucf.dto;

/**
 * @author Robert Nichols
 * @category Data Transfer Objects
 */

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
