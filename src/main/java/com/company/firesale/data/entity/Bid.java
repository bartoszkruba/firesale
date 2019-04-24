package com.company.firesale.data.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double yourBid;
    @CreationTimestamp
    private LocalDateTime lastBid;

    @ManyToOne
    private Auction auction;


    @ManyToOne
    private User user;

    public Bid(){
    }

    public Double getYourBid() {
        return yourBid;
    }

    public Bid setYourBid(Double yourBid) {
        this.yourBid = yourBid;
        return this;
    }

    public Auction getAuction(){
        return auction;
    }

    public Bid setAuction(Auction auction){
        this.auction = auction;
        return this;
    }


    public User getUser(){
        return user;
    }

    public Bid setUser(User user){
        this.user = user;
        return this;
    }

}
