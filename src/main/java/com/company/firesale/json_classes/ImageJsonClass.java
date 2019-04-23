package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Image;

public class ImageJsonClass {

    private Long id;
    private String filepath;
    private Long auctionId;

    public ImageJsonClass() {
    }

    public ImageJsonClass(Image image) {
        this.id = image.getId();
        this.filepath = image.getFilepath();
        this.auctionId = image.getAuction().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }
}
