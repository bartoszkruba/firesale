package com.company.firesale.service;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.Bid;
import com.company.firesale.data.entity.User;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.json_classes.AuctionJsonClass;
import com.company.firesale.json_classes.BidJsonClass;
import com.company.firesale.json_classes.BidNewJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final SocketService socketService;

    @Autowired
    public BidService(BidRepository bidRepository, UserService userService, AuctionService auctionService, SocketService socketService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.auctionService = auctionService;
        this.socketService = socketService;
    }

    public void addBid(Bid bid) {
        bidRepository.save(bid);
    }

    public List<BidJsonClass> findFiveByValue(int page, Long id) {
        List<BidJsonClass> bids = new ArrayList<>();
        Pageable PageWithTen = PageRequest.of(page, 5);
        bidRepository.findByAuction_IdOrderByValueDesc(id, PageWithTen).forEach(b -> bids.add(new BidJsonClass(b)));
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
            this.sendNotification(DBBid);
            bidRepository.save(DBBid);
            return new ResponseEntity<>(new BidJsonClass(DBBid), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private boolean validateBidForm(BidNewJsonClass bid, Long id) {
        LocalDateTime currentTime = LocalDateTime.now();
        AuctionJsonClass auction = auctionService.getAuctionJsonClassById(id);

        User user = userService.getUserByUsername(bid.getUsername());

        if (auction.getClosingTime().isBefore(currentTime)) { // Ej utgången aucktion
            return false;
        } else if (user.getId()
                == auction.getUser().getId()) { // Ej buda på egen auction
            return false;
        } else if (bid.getValue() <= currentPrice(auction)) { // Måste buba mer än nuvarande bud
            return false;
        } else {
            return true;
        }
    }

    public Double currentPrice(AuctionJsonClass auction) {
        Bid bid = bidRepository.findTop1ByAuction_IdOrderByValueDesc(auction.getId());
        if (bid == null) {
            return auction.getStartUpPrice();
        } else {
            return bid.getValue();
        }
    }

    private void sendNotification(Bid bid) {
        Bid latestBid = bidRepository.findTop1ByAuction_IdOrderByValueDesc(
                bid.getAuction().getId());
        if (latestBid != null &&
                !latestBid.getUser().getUsername().equals(bid.getUser().getUsername())) {
            String username = latestBid.getUser().getUsername();
            socketService.broadcastNotification(bid, username);
        }

    }


}
