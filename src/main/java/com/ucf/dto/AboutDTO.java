package com.ucf.dto;

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
