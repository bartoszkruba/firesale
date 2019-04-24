package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionJsonClass {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime openedAt;
    private LocalDateTime closingTime;
    private Double startUpPrice;
    private Double buyOutPrice;
    private String category;
    private Long user;

    private Set<Long> images = new HashSet<>();

    public AuctionJsonClass(Auction auction) {
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.openedAt = auction.getOpenedAt();
        this.closingTime = auction.getClosingTime();
        this.startUpPrice = auction.getStartUpPrice();
        this.buyOutPrice = auction.getBuyOutPrice();
        this.category = auction.getCategory().getName();

        if (auction.getUser() != null) {
            this.user = auction.getUser().getId();
        }

        if (auction.getImages() != null) {
            this.images = auction.getImages().stream().map(Image::getId).collect(Collectors.toSet());
        }
    }

}
