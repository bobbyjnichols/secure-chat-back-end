package com.ucf.service;

import com.ucf.entity.Conversation;
import com.ucf.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService {
    Conversation newConversation(List<User> participants);
}
