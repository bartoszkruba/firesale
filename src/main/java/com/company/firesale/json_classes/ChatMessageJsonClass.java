package com.company.firesale.json_classes;

import com.company.firesale.data.entity.ChatMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageJsonClass {

    private Long id;
    private Long conversationId;
    private String username;
    private String textContent;
    private LocalDateTime createdAt;

    public ChatMessageJsonClass(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.conversationId = chatMessage.getConversation().getId();
        this.username = chatMessage.getSender().getUsername();
        this.textContent = chatMessage.getTextContent();
        this.createdAt = chatMessage.getCreatedAt();

    }
}