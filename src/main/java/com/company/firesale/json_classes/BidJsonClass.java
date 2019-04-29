package com.company.firesale.json_classes;


import com.company.firesale.data.entity.Bid;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BidJsonClass {
    private Long id;
    private Double value;
    private LocalDateTime creationTime;
    private Long auctionId;
    private Long userId;
    private String username;

    public BidJsonClass(Bid bid) {
        this.id = bid.getId();
        this.creationTime = bid.getCreationTime();
        this.value = bid.getValue();

        if (bid.getUser() != null) {
            this.userId = bid.getUser().getId();
            this.username = bid.getUser().getUsername();
        }
        if (bid.getAuction() != null) {
            this.auctionId = bid.getAuction().getId();
        }
    }

}
