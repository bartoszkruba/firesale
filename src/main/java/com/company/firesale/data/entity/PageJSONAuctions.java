package com.company.firesale.data.entity;

import com.company.firesale.json_classes.AuctionJsonClass;
import lombok.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageJSONAuctions {

    private int currentPage;
    private int totalPages;
    private Set<AuctionJsonClass> list;

}
