package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Conversation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConversationJsonClass {

    private Long id;

    @Builder.Default
    private Set<UserJsonClass> members = new HashSet<>();

    public ConversationJsonClass(Conversation conversation) {

        this.id = conversation.getId();
        this.members = conversation.getMembers().stream()
                .map(m -> UserJsonClass.builder().id(m.getId()).username(m.getUsername()).build())
                .collect(Collectors.toSet());
    }
}
