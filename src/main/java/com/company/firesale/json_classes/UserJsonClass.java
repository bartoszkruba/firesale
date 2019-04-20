package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.User;


import java.util.Set;
import java.util.stream.Collectors;

public class UserJsonClass {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;


    private Set<Long> auctions;

    public UserJsonClass(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();

        if (user.getAuctions() != null) {
            this.auctions = user.getAuctions().stream().map(Auction::getId).collect(Collectors.toSet());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
