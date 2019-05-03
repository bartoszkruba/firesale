package com.company.firesale.controller;

import com.company.firesale.json_classes.BidJsonClass;
import com.company.firesale.json_classes.BidNewJsonClass;
import com.company.firesale.service.BidService;
import com.company.firesale.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    private final BidService bidService;
    private final SocketService socketService;

    @Autowired
    public BidController(BidService bidService, SocketService socketService) {
        this.bidService = bidService;
        this.socketService = socketService;
    }


    @GetMapping("/byAuctionId/{id}")
    public List<BidJsonClass> getTenBidOrderByValue(@RequestParam int page, @PathVariable Long id) {
        return bidService.findFiveByValue(page, id);
    }


    @PostMapping
    public HttpEntity<BidJsonClass> createBidEntity(@Validated @RequestBody BidNewJsonClass bid,
                                                    Principal principal) {
        if (principal.getName() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        bid.setUsername(principal.getName());

        ResponseEntity<BidJsonClass> response = bidService.createNewBid(bid);

        if (response.getStatusCode().equals(HttpStatus.CREATED)
                && response.hasBody()) {
            socketService.broadcastBid(response.getBody());
        }

        return response;
    }

}
