package com.ucf.dto;

import com.ucf.entity.Conversation;
import com.ucf.entity.Message;

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
