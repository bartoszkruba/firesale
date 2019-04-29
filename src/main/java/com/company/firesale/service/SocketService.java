package com.company.firesale.service;


import com.company.firesale.json_classes.BidJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public SocketService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void BroadcastBid(BidJsonClass bid) {
        if (bid.getAuctionId() != null) {
            String channel = "/auctionBids/" + bid.getAuctionId();
            messagingTemplate.convertAndSend(channel, bid);
        } else {
            throw new RuntimeException("Trying to broadcast bid with no AuctionId");
        }
    }
}
