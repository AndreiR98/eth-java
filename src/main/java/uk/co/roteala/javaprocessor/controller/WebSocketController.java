package uk.co.roteala.javaprocessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/send")
    public void sendMessage(String message) {
        template.convertAndSend("/topic/messages", message);
    }
}
