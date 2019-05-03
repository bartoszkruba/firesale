package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.entity.Bid;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    private Double currentPrice;
    private AuctionStatus status;

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
        this.status = auction.getStatus();

        if (auction.getUser() != null) {
            UserJsonClass u = new UserJsonClass(auction.getUser());
            u.setAuctions(null);
            this.user = u;
        }

        if (auction.getImages() != null) {
            this.images = auction.getImages().stream().map(i ->
                    ImageJsonClass.builder()
                            .auctionId(i.getId())
                            .filepath(i.getFilepath()).build())
                    .collect(Collectors.toSet());
        }
        if (auction.getBids() != null && auction.getBids().size() > 0) {
            Bid highestBid = auction.getBids().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .collect(Collectors.toList()).get(0);
            this.currentPrice = highestBid.getValue();
        } else {
            this.currentPrice = startUpPrice;
        }
    }

}
