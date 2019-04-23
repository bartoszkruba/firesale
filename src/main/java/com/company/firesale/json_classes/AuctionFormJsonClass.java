package com.company.firesale.json_classes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AuctionFormJsonClass {

    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime closingTime;
    @NotNull
    private Double startUpPrice;
    @NotNull
    private Double buyOutPrice;
    @NotEmpty
    @NotNull
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

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
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

    @Override
    public String toString() {
        return "AuctionFormJsonClass{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", closingTime=" + closingTime +
                ", startUpPrice=" + startUpPrice +
                ", buyOutPrice=" + buyOutPrice +
                ", category='" + category + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
