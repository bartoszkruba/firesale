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

//    @GetMapping
//    List<Auction> getPageWithAuctionsBasedOnTitle(
//            @PathVariable int page,
//            @RequestParam String title) {
//
//        if(title == null){
//            title = "";
//        }
//        return actionEntityServis.findTenByTitle(title, page).getContent();
//    }

//    @GetMapping
//    Iterable<Auction> getFilteredAuctions(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) Double price,
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) boolean showAll,
//            @RequestParam int page) {
//
//        AuctionStatus status = null;
//
//        if(title == null){
//            title = "";
//        }
//
//        if (!showAll) {
//            status = AuctionStatus.OPEN;
//        }
//
//        if (price == null) {
//            price = Double.MAX_VALUE;
//        }
//
//        if (category == null) {
//            if (showAll) {
//                return actionEntityServis.findFilteredAuctionsAllCategories(title, price);
//            } else {
//                return actionEntityServis.findFilteredAuctionsOpenAllCategories(title, price, status);
//            }
//        } else {
//            if (showAll) {
//                return actionEntityServis.findFilteredAuctionsWithCategory(title, price, category);
//            } else {
//                return actionEntityServis.findFilteredAuctionsOpenWithCategory(title, price, category, status);
//            }
//        }
//
//    }

    @GetMapping
    List<AuctionJsonClass> getFilteredAuctions(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer page) {

        if (title == null) {
            title = "";
        }
        if (price == null) {
            price = Double.MAX_VALUE;
        }
        if (page == null) {
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
