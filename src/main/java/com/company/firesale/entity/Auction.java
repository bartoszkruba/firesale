package com.company.firesale.entity;

import com.company.firesale.data.entity.Image;
import com.company.firesale.data.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date openedAt;
    private Date closingTime;
    private double startUpPrice;
    private double buyOutPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "auction")
    private Set<Image> images = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private AuctionStatus status;

    public Auction() {
    }

    public void addImage(Image image) {
        this.images.add(image);
        image.setAuction(this);
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
