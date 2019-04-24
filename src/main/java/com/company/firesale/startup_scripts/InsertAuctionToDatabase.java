package com.company.firesale.startup_scripts;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.entity.Category;
import com.company.firesale.data.repository.AuctionEntityRepository;
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

    @Autowired
    public InsertAuctionToDatabase(AuctionService auctionService, CategoryRepository categoryRepository) {
        this.auctionEntityRepository = auctionEntityRepository;
        this.auctionService = auctionService;
        this.categoryRepository = categoryRepository;
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
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt1).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction2 = new Auction();
        auction2.setTitle("A Blue CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(85000)
                .setStartUpPrice(15000)
                .setClosingTime(Timestamp.valueOf(ldt2).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.CLOSED);
        auctionService.addAuction(auction2);
        LocalDateTime ldt3 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction3 = new Auction();
        auction3.setTitle("A DVD")
                .setDescription("A DVD Don`t know whats on it becus has no DVD player")
                .setBuyOutPrice(20)
                .setStartUpPrice(5)
                .setClosingTime(Timestamp.valueOf(ldt3).toLocalDateTime())
                .setCategory(categories.get(2))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction3);

        LocalDateTime ldt4 = LocalDateTime.of(2019, 4, 5, 11, 30);
        Auction auction4 = new Auction();
        auction4.setTitle("Tabel")
                .setDescription("Oak tabel")
                .setBuyOutPrice(400)
                .setStartUpPrice(25)
                .setClosingTime(Timestamp.valueOf(ldt4).toLocalDateTime())
                .setCategory(categories.get(3))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction4);

        LocalDateTime ldt5 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction5 = new Auction();
        auction5.setTitle("A ORANGE CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt5).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction5);

        LocalDateTime ldt6 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction6 = new Auction();
        auction6.setTitle("A BLACK CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt6).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction6);

        LocalDateTime ldt7 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction7 = new Auction();
        auction7.setTitle("A GREEN CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt7).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction7);

        LocalDateTime ldt8 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction8 = new Auction();
        auction8.setTitle("A WHITE CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt8).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction8);

        LocalDateTime ldt9 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction9 = new Auction();
        auction9.setTitle("A GOLD CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt9).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction9);

        LocalDateTime ldt10 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction10 = new Auction();
        auction10.setTitle("A SILVER CAR")
                .setDescription("BRUM BRUM")
                .setBuyOutPrice(80000)
                .setStartUpPrice(10000)
                .setClosingTime(Timestamp.valueOf(ldt10).toLocalDateTime())
                .setCategory(categories.get(1))
                .setStatus(AuctionStatus.OPEN);
        auctionService.addAuction(auction10);
    }
}