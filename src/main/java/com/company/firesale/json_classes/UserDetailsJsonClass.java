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
    public class UserDetailsJsonClass {
        private Long id;
        private String userName;
        private String email;
        private String phoneNumber;
        private String firstName;
        private String lastName;

    @Builder.Default
    private Set<Long> auctions = new HashSet<>();

        public UserDetailsJsonClass(User user) {
            this.id = user.getId();
            this.userName = user.getUsername();
            this.email = user.getEmail();
            this.phoneNumber = user.getPhoneNumber();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();

            if (user.getAuctions() != null) {
                this.auctions = user.getAuctions().stream().map(Auction::getId).collect(Collectors.toSet());
            }
        }
    }

