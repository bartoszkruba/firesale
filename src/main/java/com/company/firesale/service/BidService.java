package com.company.firesale.service;

import com.company.firesale.data.entity.Bid;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.json_classes.BidJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<BidJsonClass> findValueByAuctionId(Long id){
        List<BidJsonClass> bids = new ArrayList<>();
        bidRepository.findByAuction_IdOrderByValueDesc(id).forEach(b -> bids.add(new BidJsonClass(b)));
        return bids;
    }

}
