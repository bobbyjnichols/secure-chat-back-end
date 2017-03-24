package com.ucf.util;

import com.ucf.dto.SocketMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Robert Nichols
 * @category Utilities
 */

@Component
public class SocketUtil {

    public SocketUtil() {
        super();
    }

    @Autowired
    @Qualifier("brokerMessagingTemplate")
    private SimpMessagingTemplate simpMessagingTemplate;

    public void socketPushMessage(SocketMessageDTO message) {
        simpMessagingTemplate.convertAndSend("/notify/event", message);
    }

    ;
}