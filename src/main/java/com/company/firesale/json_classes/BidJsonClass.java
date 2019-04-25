package com.company.firesale.json_classes;


import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidJsonClass {
    private Long id;
    private Double value;
    private LocalDateTime creationTime;
    private Long auctionId;
    private Long userid;

    public BidJsonClass(Bid bid) {
        this.id = bid.getId();
        this.creationTime = bid.getCreationTime();
        this.value = bid.getValue();

        if (bid.getUser() != null) {
            this.userid = bid.getUser().getId();
        }
        if(bid.getAuction() != null){
            this.auctionId = bid.getAuction().getId();
        }
    }

}
