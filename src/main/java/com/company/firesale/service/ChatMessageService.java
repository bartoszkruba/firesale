package com.company.firesale.service;

import com.company.firesale.data.entity.ChatMessage;
import com.company.firesale.data.repository.ChatMessageRepository;

import com.company.firesale.json_classes.ChatMessageJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public Set<ChatMessageJsonClass> getChatMessagesByConversation(long id){
        Set<ChatMessageJsonClass> messages = new HashSet<>();
        chatMessageRepository.findByConversation_IdOrderByCreatedAtAsc(id).forEach(message -> {
            messages.add(new ChatMessageJsonClass(message));
        });
        return messages;
    }



}
