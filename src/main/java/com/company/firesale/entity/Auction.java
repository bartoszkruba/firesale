package com.company.firesale.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title = null;
    @Column(columnDefinition = "TEXT")
    private String description = null;
    private Date openedAt = null;
    private Date closingTime = null;
    private double startUpPrice = 0;
    private double buyOutPrice = 0;

    @Enumerated(value = EnumType.STRING)
    private AuctionStatus status;


    public Auction() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getOpenedAt() {
        return openedAt;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public double getStartUpPrice() {
        return startUpPrice;
    }

    public double getBuyOutPrice() {
        return buyOutPrice;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpenedAt(Date openedAt) {
        this.openedAt = openedAt;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public void setStartUpPrice(double startUpPrice) {
        this.startUpPrice = startUpPrice;
    }

    public void setBuyOutPrice(double buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }
}
