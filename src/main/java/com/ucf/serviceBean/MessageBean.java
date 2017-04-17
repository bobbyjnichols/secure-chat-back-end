package com.ucf.serviceBean;

import com.ucf.entity.Conversation;
import com.ucf.entity.Message;
import com.ucf.repository.MessageRepository;
import com.ucf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* Components contain logic that is essential to the operations carried out by the server.
* The logic in these components is separate from their associated controllers so that they
* can be used elsewhere in the project.
*
* The message component facilitates in the creation of a new message. It also provides a means
* of paginating the requests for messages.
* */

@Component
public class MessageBean implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message newMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> getMessages(Conversation conversation, Pageable pageable) {
        List<Integer> keys = messageRepository.getMessages(conversation);
        return (keys != null) ? messageRepository.findByKeyIn(keys, pageable) : null;
    }
}
