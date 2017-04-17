package com.ucf.controller;

import com.ucf.dto.ConversationDTO;
import com.ucf.dto.MessageDTO;
import com.ucf.dto.UserDTO;
import com.ucf.entity.Conversation;
import com.ucf.entity.Message;
import com.ucf.entity.User;
import com.ucf.service.ConversationService;
import com.ucf.service.MessageService;
import com.ucf.service.UserService;
import com.ucf.util.ListUtils;
import com.ucf.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
* Controllers define endpoints that are available to accept RESTful requests and
* route them to the appropriate services.
*
* The messaging controller provides all the endpoints associated with sending and
* receiving messages.
* This endpoint requires authentication.
* */

@RestController
@RequestMapping(value = "/messaging")
public class MessagingController {

    @Autowired
    private Response response;

    @Autowired
    private UserService userService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(MessagingController.class);

    @RequestMapping(value = "/conversation",produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> newConversation(
            @RequestParam(name = "participants") User[] participants,
            @RequestParam(name = "name") String name
            ) {
        Conversation conversation = conversationService.newConversation(name, Arrays.asList(participants));
        return response.respond(new ConversationDTO(conversation));
    }

    @RequestMapping(value = "/conversation",produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getConversations() {
        return response.respond(ListUtils.parallelTransform(userService.getCurrentUser().getConversations(), ConversationDTO::new));
    }

    @RequestMapping(value = "/conversation/{conversation}",produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getConversation(
            @PathVariable("conversation") Conversation conversation
    ) {
        return response.respond(new ConversationDTO(conversation));
    }

    @RequestMapping(value = "/{conversation}",produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getMessages(
            @PathVariable("conversation") Conversation conversation,
            @PageableDefault(size = 25) Pageable pageable
    ) {
        return response.respond(
                messageService.getMessages(conversation, pageable)
                        .map(MessageDTO::new)
        );
    }

    @RequestMapping(value = "/message",produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> newMessage(
            @RequestParam(name = "conversation") Conversation conversation,
            @RequestParam(name = "message") String body
    ) {
        User user = userService.getCurrentUser();
        Message message = messageService.newMessage(new Message(user,conversation,body));
        return response.respond(new MessageDTO(message));
    }
}
