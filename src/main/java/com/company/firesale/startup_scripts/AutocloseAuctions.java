package com.company.firesale.startup_scripts;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.repository.AuctionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AutocloseAuctions {

    private final AuctionEntityRepository auctionEntityRepository;

    @Autowired
    public AutocloseAuctions(AuctionEntityRepository auctionEntityRepository) {
        this.auctionEntityRepository = auctionEntityRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {

        new Thread(() -> {
            List<Auction> auctions;

            while (true) {
                auctions = auctionEntityRepository.findAllByClosingTimeBeforeAndStatus(LocalDateTime.now(),
                        AuctionStatus.OPEN);
                auctions.forEach(a -> a.setStatus(AuctionStatus.CLOSED));

                auctionEntityRepository.saveAll(auctions);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).run();
    }
}
