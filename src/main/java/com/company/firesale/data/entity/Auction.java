package com.company.firesale.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @CreationTimestamp
    private LocalDateTime openedAt;
    @UpdateTimestamp
    private LocalDateTime setLastUppdate;
    private LocalDateTime closingTime;
    private Double startUpPrice;
    private Double buyOutPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "auction")
    private Set<Image> images = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private AuctionStatus status;

    @ManyToOne
    private Category category;

    public Auction() {

    }

    public void addImage(Image image) {
        this.images.add(image);
        image.setAuction(this);
    }

    public Long getId() {
        return id;
    }

    public Auction setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Auction setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Auction setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public Auction setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
        return this;
    }

    public LocalDateTime getSetLastUppdate() {
        return setLastUppdate;
    }

    public Auction setLastUppdate(LocalDateTime setLastUppdate) {
        this.setLastUppdate = setLastUppdate;
        return this;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public Auction setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
        return this;
    }

    public double getStartUpPrice() {
        return startUpPrice;
    }

    public Auction setStartUpPrice(double startUpPrice) {
        this.startUpPrice = startUpPrice;
        return this;
    }

    public Auction setBuyOutPrice(double buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
        return this;
    }
    public Double getBuyOutPrice() {
        return this.buyOutPrice;
    }

    public Category getCategory() {
        return category;
    }

    public Auction setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Auction setUser(User user) {
        this.user = user;
        return this;
    }

    public Set<Image> getImages() {
        return images;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }
}
