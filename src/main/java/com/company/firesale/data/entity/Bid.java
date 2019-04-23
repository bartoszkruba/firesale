package com.company.firesale.data.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double yourBid;
    @CreationTimestamp
    private LocalDateTime lastBid;


    public Bid(){
    }

    public Double getYourBid() {
        return yourBid;
    }

    public Bid setYourBid(Double yourBid) {
        this.yourBid = yourBid;
        return this;
    }
}
