package com.company.firesale.controller;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.json_classes.AuctionFormJsonClass;
import com.company.firesale.json_classes.AuctionJsonClass;
import com.company.firesale.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    private AuctionService actionEntityServis;

    @Autowired
    public AuctionController(AuctionService actionEntityServis) {
        this.actionEntityServis = actionEntityServis;
    }

    @GetMapping
    Iterable<Auction> getFilteredAuctions(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) boolean showAll) {

        AuctionStatus status = null;
        if (!showAll) {
            status = AuctionStatus.OPEN;
        }

        if (price == null) {
            price = Double.MAX_VALUE;
        }

        if (category == null) {
            if (showAll) {
                return actionEntityServis.findFilteredAuctionsAllCategories(title, price);
            } else {
                return actionEntityServis.findFilteredAuctionsOpenAllCategories(title, price, status);
            }
        } else {
            if (showAll) {
                return actionEntityServis.findFilteredAuctionsWithCategory(title, price, category);
            } else {
                return actionEntityServis.findFilteredAuctionsOpenWithCategory(title, price, category, status);
            }
        }

    }

    @PostMapping
        // TODO: 2019-04-23 Should return JsonClas
    HttpEntity<Auction> createActionEntity(@Validated @RequestBody AuctionFormJsonClass auction,
                                           Principal principal) {
        return actionEntityServis.createNewAuction(auction, principal.getName());
    }

    @GetMapping("/{page}")
    List<Auction> getTenActionEntityOrderById(@PathVariable int page) {
        return actionEntityServis.findTenById(page).getContent();
    }

    @GetMapping("/date/{page}")
    List<Auction> getTenActionEntityOrderByDate(@PathVariable int page) {
        return actionEntityServis.findTenByDate(page).getContent();
    }

    @GetMapping("/id/{id}")
    AuctionJsonClass getAuctionById(@PathVariable long id) {
        return actionEntityServis.findById(id);
    }

}
