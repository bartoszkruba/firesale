package com.company.firesale.controller;

import com.company.firesale.data.entity.PageJSONAuctions;
import com.company.firesale.json_classes.AuctionFormJsonClass;
import com.company.firesale.json_classes.AuctionJsonClass;
import com.company.firesale.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    private AuctionService actionEntityServis;

    @Autowired
    public AuctionController(AuctionService actionEntityServis) {
        this.actionEntityServis = actionEntityServis;
    }

    @GetMapping
    PageJSONAuctions getFilteredAuctions(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "All") String category,
            @RequestParam(required = false, defaultValue = "false") Boolean showAll,
            @RequestParam(required = false, defaultValue = "0") Double page){

        return actionEntityServis.findFiveByTitleAndStatus(title, category, showAll, page.intValue());
    }

    @PostMapping
    public HttpEntity<AuctionJsonClass> createActionEntity(@Validated @RequestBody AuctionFormJsonClass auction,
                                                           Principal principal) {
        return actionEntityServis.createNewAuction(auction, principal.getName());
    }

    @GetMapping("/id/{id}")
    AuctionJsonClass getAuctionById(@PathVariable long id) {
        return actionEntityServis.findById(id);
    }

}
