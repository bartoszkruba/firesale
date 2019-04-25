package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;
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
    private UserJsonClass user;
    private BidJsonClass higestbid;

    @Builder.Default
    private Set<ImageJsonClass> images = new HashSet<>();

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
            this.user = new UserJsonClass(auction.getUser());
        }

        if (auction.getImages() != null) {
            this.images = auction.getImages().stream().map(i ->
                    ImageJsonClass.builder()
                            .auctionId(i.getId())
                            .filepath(i.getFilepath()).build())
                    .collect(Collectors.toSet());
        }

        if (auction.getBids() != null) {
            this.higestbid = new BidJsonClass(auction.getBids().get(auction.getBids().size() - 1));
        }
    }

}
