package com.company.firesale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auctions")
public class AuctionFilterController {

    @GetMapping()
    public Map getFilteredAuctions(@RequestParam Map<String, String> allParams) {
        return allParams;
    }


}
