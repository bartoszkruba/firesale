package com.company.firesale.startup_scripts;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.entity.Bid;
import com.company.firesale.data.entity.Category;
import com.company.firesale.data.repository.AuctionEntityRepository;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertAuctionToDatabase implements CommandLineRunner {

    private AuctionEntityRepository auctionEntityRepository;
    private AuctionService auctionService;
    private CategoryRepository categoryRepository;
    private BidRepository bidRepository;

    @Autowired
    public InsertAuctionToDatabase(AuctionService auctionService, CategoryRepository categoryRepository, BidRepository bidRepository) {
        this.auctionEntityRepository = auctionEntityRepository;
        this.auctionService = auctionService;
        this.categoryRepository = categoryRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Categories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("All"));
        categories.add(new Category("Cars"));
        categories.add(new Category("Electronics"));
        categories.add(new Category("Furniture"));
        categories.forEach(category -> categoryRepository.save(category));

        LocalDateTime ldt1 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction1 = new Auction();
        auction1.setTitle("A RED CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(Double.parseDouble("80000"))
                .setStartUpPrice(Double.parseDouble("10000"))
                .setClosingTime(Timestamp.valueOf(ldt1).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction2 = new Auction();
        auction2.setTitle("A Blue CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(Double.parseDouble("85000"))
                .setStartUpPrice(Double.parseDouble("15000"))
                .setClosingTime(Timestamp.valueOf(ldt2).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.CLOSED);
        auctionService.addAuction(auction2);
        LocalDateTime ldt3 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction3 = new Auction();
        auction3.setTitle("A DVD")
                .setDescription("A DVD Don`t know whats on it becus has no DVD player")
                .setBuyOutPrice(Double.parseDouble("20"))
                .setStartUpPrice(Double.parseDouble("5"))
                .setClosingTime(Timestamp.valueOf(ldt3).toLocalDateTime())
                .setCategory(categories.get(2))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction3);

        LocalDateTime ldt4 = LocalDateTime.of(2019, 4, 5, 11, 30);
        Auction auction4 = new Auction();
        auction4.setTitle("Tabel")
                .setDescription("Oak tabel")
                .setBuyOutPrice(Double.parseDouble("400"))
                .setStartUpPrice(Double.parseDouble("25"))
                .setClosingTime(Timestamp.valueOf(ldt4).toLocalDateTime())
                .setCategory(categories.get(3))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction4);

        Bid bid1 = new Bid();
        bid1.setYourBid(Double.parseDouble("20000"));
        bid1.setAuction(auction1);
        bidRepository.save(bid1);

        Bid bid2 = new Bid();
        bid2.setYourBid(Double.parseDouble("250"));
        bid2.setAuction(auction4);
        bidRepository.save(bid2);

        Bid bid3 = new Bid();
        bid3.setYourBid(Double.parseDouble("300"));
        bid3.setAuction(auction4);
        bidRepository.save(bid3);

        Bid bid4 = new Bid();
        bid4.setYourBid(Double.parseDouble("400"));
        bid4.setAuction(auction4);
        bidRepository.save(bid4);
    }
}