package com.company.firesale.controller;


import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.Bid;
import com.company.firesale.json_classes.BidJsonClass;
import com.company.firesale.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    /*@GetMapping("/byAuction/{id}")
    public List<BidJsonClass> getBidByAuction(@PathVariable Long id){
        return bidService.findValueByAuctionId(id);
    }*/


    @GetMapping("/byAuctionId/{id}")
    public List<BidJsonClass> getTenBidOrderByValue(@RequestParam int page, @PathVariable Long id) {
        return bidService.findFiveByValue(page, id);
    }



}
