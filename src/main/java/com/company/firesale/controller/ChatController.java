package com.company.firesale.controller;

import com.company.firesale.json_classes.ChatMessageJsonClass;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    public void sendChatMessage(ChatMessageJsonClass message, Principal principal) {
        System.out.println("Message Received from user: " + principal.getName());
        System.out.println("Message: " + message.getTextContent());
        System.out.println("Sending to conversation ID: " + message.getConversationId());



    }
}
