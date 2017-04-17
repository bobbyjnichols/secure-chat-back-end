package com.ucf.serviceBean;

import com.ucf.entity.Conversation;
import com.ucf.entity.User;
import com.ucf.repository.ConversationRepository;
import com.ucf.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The conversation component facilitates in the creation of a new conversation and assigning
* values to that conversation.
* */

@Component
public class ConversationBean implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation newConversation(String name, List<User> participants) {
        Conversation conversation = new Conversation();
        conversation.setUsers(participants);
        conversation.setName(name);
        return conversationRepository.save(conversation);
    }

}
