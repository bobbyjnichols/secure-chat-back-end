package com.ucf.dto;

import com.ucf.entity.Conversation;
import com.ucf.util.ListUtils;

import java.util.List;

/*
* Data Transfer Objects (or DTOs) define what from an object should be returned as JSON as a
* response to a request. These classes define the what information is accessible to users and
* acts as a layer of security as well as a way to increase response performance.
*
* The Conversation DTO defines the properties available when querying for a conversation.
* The DTO does not include its child messages because they are available in a separate
* query and messages aren't always needed when requesting a conversation.
* */

public class ConversationDTO {
    private Integer key;
    private final List<UserDTO> participants;
    private String name;

    public ConversationDTO(Conversation conversation) {
        this.key = conversation.getKey();
        this.participants = ListUtils.parallelTransform(conversation.getUsers(), UserDTO::new);
        this.name = conversation.getName();
    }

    public Integer getKey() {
        return key;
    }

    public List<UserDTO> getParticipants() {
        return participants;
    }

    public String getName() {
        return name;
    }
}
