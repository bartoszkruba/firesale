package com.company.firesale.service;


import com.company.firesale.data.entity.Bid;
import com.company.firesale.json_classes.BidJsonClass;
import com.company.firesale.json_classes.BidNotificationJsonClass;
import com.company.firesale.json_classes.ChatMessageJsonClass;
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

    public void broadcastBid(BidJsonClass bid) {
        if (bid.getAuctionId() != null) {
            String channel = "/auctionBids/" + bid.getAuctionId();
            messagingTemplate.convertAndSend(channel, bid);
        } else {
            throw new RuntimeException("Trying to broadcast bid with no AuctionId");
        }
    }

    public void broadcastNotification(Bid bid, String username) {
        messagingTemplate.convertAndSendToUser(username, "/queue/notifications", new BidNotificationJsonClass(bid));
    }

    public void broadcastChatMessage(ChatMessageJsonClass message, String username) {
        messagingTemplate.convertAndSendToUser(username, "/queue/chat/messages", message);
    }
}
