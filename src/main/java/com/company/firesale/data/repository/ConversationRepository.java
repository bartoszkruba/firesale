package com.company.firesale.data.repository;

import com.company.firesale.data.entity.ChatMessage;
import com.company.firesale.data.entity.Conversation;
import com.company.firesale.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Set<Conversation> findConversationsByMembersContainingOrderByUpdatedAt(User user);

}
