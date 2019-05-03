package com.company.firesale.service;

import com.company.firesale.data.entity.ChatMessage;
import com.company.firesale.data.entity.Conversation;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.ChatMessageRepository;
import com.company.firesale.json_classes.ChatMessageJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final ConversationService conversationService;
    private final SocketService socketService;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository, UserService userService, ConversationService conversationService, SocketService socketService) {
        this.chatMessageRepository = chatMessageRepository;
        this.userService = userService;
        this.conversationService = conversationService;
        this.socketService = socketService;
    }

    @Transactional
    public List<ChatMessageJsonClass> getChatMessagesByConversation(Long id, String username) {

        User user = userService.getUserByUsername(username);
        if (user == null) return null;

        Conversation conversation = conversationService.getConversationById(id);
        if (conversation.getMembers().stream().noneMatch(m -> m.getUsername().equals(username))) return null;

        return conversation.getMessages().stream()
                .sorted(Comparator.comparing(ChatMessage::getCreatedAt))
                .map(ChatMessageJsonClass::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void postMessage(ChatMessageJsonClass message, String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) return;

        Conversation conversation = conversationService.getConversationById(message.getConversationId());
        if (conversation == null) return;

        if (conversation.getMembers().stream().noneMatch(m -> m.getUsername().equals(username))) return;

        ChatMessage DBMessage = ChatMessage.builder()
                .sender(user)
                .textContent(message.getTextContent())
                .conversation(conversation).build();

        chatMessageRepository.save(DBMessage);

        message = new ChatMessageJsonClass(DBMessage);

        for (User u : conversation.getMembers()) {
            socketService.broadcastChatMessage(message, u.getUsername());
        }
    }

}
