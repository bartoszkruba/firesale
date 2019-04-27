package com.company.firesale.service;

import com.company.firesale.data.entity.*;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.json_classes.AuctionJsonClass;
import com.company.firesale.json_classes.BidJsonClass;
import com.company.firesale.json_classes.BidNewJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BidService {

    private BidRepository bidRepository;
    private final UserService userService;
    private AuctionService auctionService;

    @Autowired
    public BidService(BidRepository bidRepository, UserService userService, AuctionService auctionService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    public void addBid(Bid bid) {
        bidRepository.save(bid);
    }

  /*  public List<BidJsonClass> findValueByAuctionId(Long id){
        List<BidJsonClass> bids = new ArrayList<>();
        bidRepository.findByAuction_IdOrderByValueDesc(id).forEach(b -> bids.add(new BidJsonClass(b)));
        return bids;
    }*/

    public Bid curentHigestBid(Long id){
        return bidRepository.findByAuction_IdOrderByValueDesc(id);
    }


    public List<BidJsonClass> findFiveByValue(int page, Long id) {
        List<BidJsonClass> bids = new ArrayList<>();
        Pageable PageWithTen = PageRequest.of(page, 5);
        bidRepository.findByAuction_IdOrderByValueDesc(id,PageWithTen).forEach(b -> bids.add(new BidJsonClass(b)));
        return bids;
    }



    public ResponseEntity<BidJsonClass> createNewBid(BidNewJsonClass bid) {
        User user = userService.getUserByUsername(bid.getUsername());
        Auction auction = auctionService.getAuctionById(bid.getAuctionId());

        if (validateBidForm(bid, bid.getAuctionId()) && user != null && auction != null) {
            Bid DBBid = new Bid();
            user.addBid(DBBid);
            DBBid.setValue(bid.getValue());
            DBBid.setAuction(auction);
            DBBid.setUser(user);

            bidRepository.save(DBBid);

            return new ResponseEntity<>(new BidJsonClass(DBBid), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private boolean validateBidForm(BidNewJsonClass bid ,Long id) {
        LocalDateTime currentTime = LocalDateTime.now();
        AuctionJsonClass auction = auctionService.getAuctionJsonClassById(id);

        User user = userService.getUserByUsername(bid.getUsername());

     /*   if (auction.getClosingTime().isBefore(currentTime)) {
            return false;
        }else if (bid.getValue() <= curentHigestBid(id).getValue()) {
            return false;
        }else if (bid.getValue() > auction.getBuyOutPrice()) { //TODO if tru chanche auctionId to close
            return false;
        }else if (user.getId() == auction.getUser().getId()){
                return false;
        }else {
            return true;
        }*/
        return true;
    }


}
