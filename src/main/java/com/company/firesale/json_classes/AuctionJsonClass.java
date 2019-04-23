package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;

import java.time.LocalDateTime;

public class AuctionJsonClass {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime openedAt;
    private LocalDateTime closingTime;
    private double startUpPrice;
    private double buyOutPrice;
    private String category;

    public AuctionJsonClass(Auction auction){
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.openedAt = auction.getOpenedAt();
        this.closingTime = auction.getClosingTime();
        this.startUpPrice = auction.getStartUpPrice();
        this.buyOutPrice = auction.getBuyOutPrice();
        this.category = auction.getCategory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public double getStartUpPrice() {
        return startUpPrice;
    }

    public void setStartUpPrice(double startUpPrice) {
        this.startUpPrice = startUpPrice;
    }

    public double getBuyOutPrice() {
        return buyOutPrice;
    }

    public void setBuyOutPrice(double buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
