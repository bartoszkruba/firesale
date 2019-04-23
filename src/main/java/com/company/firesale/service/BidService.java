package com.company.firesale.service;

import com.company.firesale.data.entity.Bid;
import com.company.firesale.data.entity.Category;
import com.company.firesale.data.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    private BidRepository bidRepository;

    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public void addBid(Bid bid) {
        bidRepository.save(bid);
    }


}
