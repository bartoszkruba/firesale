package com.company.firesale.controller;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.json_classes.AuctionFormJsonClass;
import com.company.firesale.json_classes.AuctionJsonClass;
import com.company.firesale.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    private AuctionService actionEntityServis;

    @Autowired
    public AuctionController(AuctionService actionEntityServis) {
        this.actionEntityServis = actionEntityServis;
    }

    @GetMapping
    List<Auction> getFilteredAuctions(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer page){

        if (title == null) {
            title = "";
        }
        if(price == null) {
            price = Double.MAX_VALUE;
        }
        if(page == null){
            page = 0;
        }

        return actionEntityServis.findTenByTitle(title, page);
    }

    // TODO: 2019-04-23 Should return JsonClas
    @PostMapping
    public HttpEntity<AuctionJsonClass> createActionEntity(@Validated @RequestBody AuctionFormJsonClass auction,
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
    Auction getAuctionById(@PathVariable long id) {
        return actionEntityServis.findById(id);
    }

}
