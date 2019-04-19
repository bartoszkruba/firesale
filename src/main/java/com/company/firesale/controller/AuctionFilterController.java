package com.company.firesale.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/api/auctions")
public class AuctionFilterController {

    @GetMapping()
    public Map getFilteredAuctions(@RequestParam Map<String, String> allParams) {
        return allParams;
    }

}
