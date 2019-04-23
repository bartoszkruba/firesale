package com.company.firesale.startup_scripts;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.repository.AuctionEntityRepository;
import com.company.firesale.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class InsertAuctionToDatabase implements CommandLineRunner {

    private final AuctionEntityRepository auctionEntityRepository;
    private final AuctionService auctionService;

    @Autowired
    public InsertAuctionToDatabase(AuctionEntityRepository auctionEntityRepository, AuctionService auctionService) {
        this.auctionEntityRepository = auctionEntityRepository;
        this.auctionService = auctionService;
    }

    @Override
    public void run(String... args) throws Exception {
        LocalDateTime ldt1 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction1 = new Auction();
        auction1.setTitle("A RED CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt1).toLocalDateTime())
//                .setCategory("Cars")
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction2 = new Auction();
        auction2.setTitle("A Blue CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(85000)
                .setStartUpPrice(15000)
                .setClosingTime(Timestamp.valueOf(ldt2).toLocalDateTime())
                .setStatus(AuctionStatus.CLOSED);
        auctionService.addAuction(auction2);

        LocalDateTime ldt3 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction3 = new Auction();
        auction3.setTitle("A DVD")
                .setDescription("A DVD Don`t know whats on it becus has no DVD player")
                .setBuyOutPrice(20)
                .setStartUpPrice(5)
                .setClosingTime(Timestamp.valueOf(ldt3).toLocalDateTime())
//                .setCategory("Electronics")
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction3);

        LocalDateTime ldt4 = LocalDateTime.of(2019, 4, 5, 11, 30);
        Auction auction4 = new Auction();
        auction4.setTitle("Meat")
                .setDescription(" 5.5Kg Meat of a ... animal")
                .setBuyOutPrice(400)
                .setStartUpPrice(25)
                .setClosingTime(Timestamp.valueOf(ldt4).toLocalDateTime())
//                .setCategory("Furniture")
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction4);
    }
}
