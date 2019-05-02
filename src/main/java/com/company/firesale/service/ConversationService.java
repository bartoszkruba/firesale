package com.company.firesale.service;

import com.company.firesale.data.entity.Conversation;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.ConversationRepository;
import com.company.firesale.json_classes.ConversationJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserService userService) {
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public Set<ConversationJsonClass> findAllForUsername_JSON(String username) {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new RuntimeException("User doesn't exist");
        }

        Set<Conversation> conversations =
                conversationRepository.findConversationsByMembersContainingOrderByUpdatedAt(user);

        return conversations.stream().map(ConversationJsonClass::new).collect(Collectors.toSet());
    }

    public Conversation getConversationById(Long id) {
        return conversationRepository.findById(id).orElse(null);
    }


    @Transactional
    public ConversationJsonClass getConversationBetweenUsers(String[] usernames, String principal) {
        User user = userService.getUserByUsername(principal);
        if (user == null) throw new RuntimeException("No user found");

        User[] users = new User[usernames.length];

        int i = 0;
        for (String username : usernames) {
            User foundUser = userService.getUserByUsername(username);
            if (foundUser != null) {
                users[i] = foundUser;
            } else {
                throw new RuntimeException("User with username [" + username + "] not found");
            }
            i++;
        }

        Set<Conversation> conversations = conversationRepository.findAllByMembers(user);

        Conversation conversation = this.findConversationWithMembers(users, conversations);

        if (conversation == null) {
            conversation = Conversation.builder().build();
            Arrays.stream(users).forEach(conversation::addMember);
            conversation = conversationRepository.save(conversation);
        }

        return new ConversationJsonClass(conversation);
    }

    private Conversation findConversationWithMembers(User[] users, Set<Conversation> conversations) {
        int requiredMatches = users.length;

        for (Conversation c : conversations) {
            int foundMatches = 0;
            for (User u : users) {
                if (c.getMembers().stream().anyMatch(m -> m.getUsername().equals(u.getUsername())))
                    foundMatches++;
                else break;
            }
            if (requiredMatches == foundMatches) return c;
        }

        return null;
    }
}
