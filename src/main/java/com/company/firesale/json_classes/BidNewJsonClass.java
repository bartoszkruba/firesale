package com.company.firesale.json_classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidNewJsonClass {
    @NotNull
    private Double value;

    @NotNull
    private Long auctionId;

    private String username;
}
