package com.ucf.serviceBean;

import com.ucf.entity.Conversation;
import com.ucf.entity.User;
import com.ucf.repository.ConversationRepository;
import com.ucf.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversationBean implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation newConversation(List<User> participants) {
        Conversation conversation = new Conversation();
        conversation.setUsers(participants);
        return conversationRepository.save(conversation);
    }

}
