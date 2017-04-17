package com.ucf.service;

import com.ucf.entity.Conversation;
import com.ucf.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
* Service classes provide interfaces for implementation beans.
* */

@Service
public interface MessageService {
    Message newMessage(Message message);

    Page<Message> getMessages(Conversation conversation, Pageable pageable);
}
