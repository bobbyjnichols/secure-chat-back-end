package com.ucf.service;

import com.ucf.entity.Conversation;
import com.ucf.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Service classes provide interfaces for implementation beans.
* */

@Service
public interface ConversationService {
    Conversation newConversation(String name, List<User> participants);
}
