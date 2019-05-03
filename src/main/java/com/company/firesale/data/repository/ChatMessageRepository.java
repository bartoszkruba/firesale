package com.company.firesale.data.repository;

import com.company.firesale.data.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

//    Set<ChatMessage> getChatMessagesByConversation_idOrOrderByCreatedAtAsc(long id);
    Set<ChatMessage> findByConversation_IdOrderByCreatedAtAsc(long id);
}
