package com.ucf.dto;

import com.ucf.entity.Conversation;
import com.ucf.util.ListUtils;

import java.util.List;

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
