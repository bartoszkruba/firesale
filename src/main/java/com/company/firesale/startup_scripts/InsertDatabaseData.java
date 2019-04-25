package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.*;
import com.company.firesale.data.repository.AuctionEntityRepository;
import com.company.firesale.data.repository.BidRepository;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.service.AuctionService;
import com.company.firesale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDatabaseData implements CommandLineRunner {

    private UserService userService;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    ///nya
    private AuctionEntityRepository auctionEntityRepository;
    private AuctionService auctionService;
    private CategoryRepository categoryRepository;
    private BidRepository bidRepository;

    @Autowired
    public InsertDatabaseData(UserService userService, RoleRepository roleRepository, BCryptPasswordEncoder encoder, AuctionService auctionService, CategoryRepository categoryRepository, BidRepository bidRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;

        this.encoder = encoder;


        ///nya
        this.auctionEntityRepository = auctionEntityRepository;
        this.auctionService = auctionService;
        this.categoryRepository = categoryRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Roles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);


        //Users
        User user1 = User.builder()
                .username("john69")
                .password(encoder.encode("password1234"))
                .email("john.doe@gmail.com")
                .address("4323 Oak Lawn Ave")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("(127)-963-1879")
                .build()
                .addRole(adminRole)
                .addRole(userRole);

        userService.saveUser(user1);

        User user2 = User.builder()
                .username("mary111")
                .password(encoder.encode("mypassword"))
                .email("mary323@gmail.com")
                .address("4968 Railroad St")
                .firstName("Mary")
                .lastName("Poppins")
                .phoneNumber("(277)-505-2351")
                .build()
                .addRole(userRole);
        userService.saveUser(user2);

        User user3 = User.builder()
                .username("ChrisL")
                .password(encoder.encode("wyatt"))
                .email("chris.lewis92@example.com")
                .address("5860 Camden Ave")
                .firstName("Chris")
                .lastName("Lewis")
                .phoneNumber("(893)-121-7525")
                .build()
                .addRole(userRole);
        userService.saveUser(user3);

        User user4 = User.builder()
                .username("Morenorator")
                .password(encoder.encode("reptile"))
                .email("ruben.moreno87@example.com")
                .address("5860 Camden Ave")
                .firstName("Ruben")
                .lastName("Moreno")
                .phoneNumber("(247)-425-7475")
                .build()
                .addRole(userRole);
        userService.saveUser(user4);

        User user5 = User.builder()
                .username("Cindirella")
                .password(encoder.encode("poopoo"))
                .email("ruben.moreno87@example.com")
                .address("1164 Adelaide Ave")
                .firstName("Cindy")
                .lastName("Stephens")
                .phoneNumber("(272)-121-1765")
                .build()
                .addRole(userRole);
        userService.saveUser(user5);

        //Categories
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("All").build());
        categories.add(Category.builder().name("Cars").build());
        categories.add(Category.builder().name("Electronics").build());
        categories.add(Category.builder().name("Furniture").build());
        categories.forEach(category -> categoryRepository.save(category));

        LocalDateTime ldt1 = LocalDateTime.of(2019, 4, 18, 10, 30);
        Auction auction1 = Auction.builder()
                .title("A RED CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("80000"))
                .startUpPrice(Double.parseDouble("10000"))
                .closingTime(Timestamp.valueOf(ldt1).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction2 = Auction.builder()
                .title("A Blue CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt2).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.CLOSED).build();
        auctionService.addAuction(auction2);

        LocalDateTime ldt3 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction3 = Auction.builder()
                .title("A DVD")
                .description("A DVD Don`t know whats on it becus has no DVD player")
                .buyOutPrice(Double.parseDouble("20"))
                .startUpPrice(Double.parseDouble("5"))
                .closingTime(Timestamp.valueOf(ldt3).toLocalDateTime())
                .category(categories.get(2))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction3);

        LocalDateTime ldt4 = LocalDateTime.of(2019, 4, 5, 11, 30);
        Auction auction4 = Auction.builder()
                .title("Table")
                .description("Oak tabel")
                .buyOutPrice(Double.parseDouble("400"))
                .startUpPrice(Double.parseDouble("25"))
                .closingTime(Timestamp.valueOf(ldt4).toLocalDateTime())
                .category(categories.get(3))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction4);

        LocalDateTime ldt5 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction5 = Auction.builder()
                .title("A Black CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt5).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction5);

        LocalDateTime ldt6 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction6 = Auction.builder()
                .title("A White CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt6).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction6);

        LocalDateTime ldt7 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction7 = Auction.builder()
                .title("A Gold CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt7).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction7);

        LocalDateTime ldt8 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction8 = Auction.builder()
                .title("A Striped CAR")
                .description("BRUM BRUM")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt8).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build();
        auctionService.addAuction(auction8);

        Bid bid1 = Bid.builder()
                .value(Double.parseDouble("20000"))
                .auction(auction1)
                .user(user2).build();
        bidRepository.save(bid1);

        Bid bid2 = Bid.builder()
                .value(Double.parseDouble("250"))
                .auction(auction4)
                .user(user3).build();
        bidRepository.save(bid2);

        Bid bid5 = Bid.builder()
                .value(Double.parseDouble("450"))
                .auction(auction4)
                .user(user5).build();
        bidRepository.save(bid5);

        Bid bid4 = Bid.builder()
                .value(Double.parseDouble("400"))
                .auction(auction4)
                .user(user1).build();
        bidRepository.save(bid4);


    }
}
