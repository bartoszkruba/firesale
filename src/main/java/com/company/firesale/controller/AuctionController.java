package com.company.firesale.controller;

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
    List<AuctionJsonClass> getFilteredAuctions(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "0") Integer page){

        return actionEntityServis.findFiveByTitle(title, page);
    }

    @GetMapping("/count")
    Integer getAuctionCount(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "0") Integer page){

        return actionEntityServis.countAuctionsByTitleContaining(title);
    }


    // TODO: 2019-04-23 Should return JsonClas
    @PostMapping
    public HttpEntity<AuctionJsonClass> createActionEntity(@Validated @RequestBody AuctionFormJsonClass auction,
                                                           Principal principal) {
        return actionEntityServis.createNewAuction(auction, principal.getName());
    }

//    @GetMapping("/{page}")
//    List<Auction> getTenActionEntityOrderById(@PathVariable int page) {
//        return actionEntityServis.findTenById(page).getContent();
//    }
//
//    @GetMapping("/date/{page}")
//    List<Auction> getTenActionEntityOrderByDate(@PathVariable int page) {
//        return actionEntityServis.findTenByDate(page).getContent();
//    }

    @GetMapping("/id/{id}")
    AuctionJsonClass getAuctionById(@PathVariable long id) {
        return actionEntityServis.findById(id);
    }

}
