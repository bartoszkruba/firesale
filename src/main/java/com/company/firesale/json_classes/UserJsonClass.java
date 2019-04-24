package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJsonClass {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;

    @Builder.Default
    private Set<Long> auctions = new HashSet<>();

    public UserJsonClass(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        if (user.getAuctions() != null) {
            this.auctions = user.getAuctions().stream().map(Auction::getId).collect(Collectors.toSet());
        }
    }
}
