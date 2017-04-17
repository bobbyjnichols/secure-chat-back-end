package com.ucf.dto;

import com.ucf.entity.Conversation;
import com.ucf.entity.Message;

/*
* Data Transfer Objects (or DTOs) define what from an object should be returned as JSON as a
* response to a request. These classes define the what information is accessible to users and
* acts as a layer of security as well as a way to increase response performance.
*
* The Message DTO defines the properties available when querying for a message object. This
* object is notably sparse because many are returned at once and usually in the context of
* a parent conversation which contains more information applicable.
* */

public class MessageDTO {
    private Integer key;
    private UserDTO user;
    private Integer conversation;
    private String text;

    public MessageDTO(Message message) {
        this.key = message.getKey();
        this.user = new UserDTO(message.getUser());
        this.conversation = message.getConversation().getKey();
        this.text = message.getText();
    }

    public Integer getKey() {
        return key;
    }

    public UserDTO getUser() {
        return user;
    }

    public Integer getConversation() {
        return conversation;
    }

    public String getText() {
        return text;
    }
}
