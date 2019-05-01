package com.company.firesale.service;

import com.company.firesale.data.entity.Conversation;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.ConversationRepository;
import com.company.firesale.data.repository.UserRepository;
import com.company.firesale.json_classes.ConversationJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public Set<ConversationJsonClass> findAllForUsername_JSON(String username) {
        User user = userRepository.findDistinctFirstByUsernameIgnoreCase(username);

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
}
