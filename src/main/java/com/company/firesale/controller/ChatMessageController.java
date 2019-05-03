package com.company.firesale.controller;

import com.company.firesale.json_classes.ChatMessageJsonClass;
import com.company.firesale.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/conversation/id")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<ChatMessageJsonClass>> getMyMessages(@PathVariable long id, Principal principal) {

        try {
            List<ChatMessageJsonClass> messages =
                    chatMessageService.getChatMessagesByConversation(id, principal.getName());
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Couldn't get messages: " + e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
