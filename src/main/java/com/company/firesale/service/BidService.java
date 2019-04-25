package com.company.firesale.service;

import com.company.firesale.data.entity.Bid;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.json_classes.BidJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  /*  public List<BidJsonClass> findValueByAuctionId(Long id){
        List<BidJsonClass> bids = new ArrayList<>();
        bidRepository.findByAuction_IdOrderByValueDesc(id).forEach(b -> bids.add(new BidJsonClass(b)));
        return bids;
    }*/

    public List<BidJsonClass> findFiveByValue(int page, Long id) {

        List<BidJsonClass> bids = new ArrayList<>();
        Pageable PageWithTen = PageRequest.of(page, 5);
        bidRepository.findByAuction_IdOrderByValueDesc(id,PageWithTen).forEach(b -> bids.add(new BidJsonClass(b)));
        return bids;
    }

}
