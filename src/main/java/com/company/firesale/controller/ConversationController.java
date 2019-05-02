package com.company.firesale.controller;

import com.company.firesale.json_classes.ConversationJsonClass;
import com.company.firesale.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

    private final ConversationService conversationService;


    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/my_conversations")
    private ResponseEntity<Set<ConversationJsonClass>> getMyConversations(Principal principal) {
        try {
            String username = principal.getName();

            Set<ConversationJsonClass> conversations = conversationService.findAllForUsername_JSON(username);

            return new ResponseEntity<>(conversations, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Couldn't get conversations: " + e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/with/{username}")
    public ResponseEntity<ConversationJsonClass> createConversation(@PathVariable String username, Principal principal) {

        String[] users = {username, principal.getName()};

        try {
            return new ResponseEntity<>(conversationService
                    .getConversationBetweenUsers(users, principal.getName()), HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
