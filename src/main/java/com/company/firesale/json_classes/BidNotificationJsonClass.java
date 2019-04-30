package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidNotificationJsonClass {

    private Long auctionId;
    private Double newHighestBid;
    private String username;
    private String auctionTitle;
    private Long userId;

    public BidNotificationJsonClass(Bid bid) {
        this.auctionTitle = bid.getAuction().getTitle();
        this.auctionId = bid.getAuction().getId();
        this.newHighestBid = bid.getValue();
        this.username = bid.getUser().getUsername();
        this.userId = bid.getUser().getId();
    }

    public BidNotificationJsonClass(BidJsonClass bid) {
        this.auctionId = bid.getAuctionId();
        this.newHighestBid = bid.getValue();
        this.username = bid.getUsername();
        this.userId = bid.getUserId();
    }
}
