package com.company.firesale.json_classes;

import java.util.Date;

public class AuctionFormJsonClass {

    private String title;
    private String description;
    private Date closingTime;
    private Double startUpPrice;
    private Double buyOutPrice;
    private String category;

    private String[] images;

    public AuctionFormJsonClass() {
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

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Double getStartUpPrice() {
        return startUpPrice;
    }

    public void setStartUpPrice(Double startUpPrice) {
        this.startUpPrice = startUpPrice;
    }

    public Double getBuyOutPrice() {
        return buyOutPrice;
    }

    public void setBuyOutPrice(Double buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
