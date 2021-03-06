package com.company.firesale.startup_scripts;


import com.company.firesale.data.entity.*;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.data.repository.RoleRepository;
import com.company.firesale.service.AuctionService;
import com.company.firesale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Component
public class InsertDatabaseData implements CommandLineRunner {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuctionService auctionService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public InsertDatabaseData(UserService userService, RoleRepository roleRepository, BCryptPasswordEncoder encoder,
                              AuctionService auctionService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.auctionService = auctionService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //Categories
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("All").build());
        categories.add(Category.builder().name("Cars").build());
        categories.add(Category.builder().name("Electronics").build());
        categories.add(Category.builder().name("Furniture").build());
        categories.add(Category.builder().name("Misc").build());
        categories.forEach(category -> categoryRepository.save(category));

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
                .firstName("Ruben")
                .lastName("Moreno")
                .phoneNumber("(247)-425-7475")
                .build()
                .addRole(userRole);
        userService.saveUser(user4);

        User user5 = User.builder()
                .username("Cinderella")
                .password(encoder.encode("poopoo"))
                .email("ruben.moreno87@example.com")
                .firstName("Cindy")
                .lastName("Stephens")
                .phoneNumber("(272)-121-1765")
                .build()
                .addRole(userRole);
        userService.saveUser(user5);


        LocalDateTime ldt1 = LocalDateTime.of(2019, 5, 18, 10, 30);
        Auction auction1 = Auction.builder()
                .title("Not a great car")
                .description("Selling my old car.")
                .buyOutPrice(8000D)
                .startUpPrice(1000D)
                .closingTime(Timestamp.valueOf(ldt1).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/1.jpeg").build())
                .addImage(Image.builder().filepath("/images/4.jpg").build())
                .addImage(Image.builder().filepath("/images/5.jpeg").build())
                .addBid(Bid.builder()
                        .value(20000D)
                        .user(user2).build());
        user1.addAuction(auction1);
        auctionService.addAuction(auction1);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 5, 5, 11, 00);
        Auction auction2 = Auction.builder()
                .title("Some sportscar I found")
                .description("Someone left this at my house after a party. Selling it to pay for the burnt down garage. Seems pretty nice.")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt2).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.CLOSED).build()
                .addImage(Image.builder().filepath("/images/2.jpg").build());
        user1.addAuction(auction2);
        auctionService.addAuction(auction2);

        LocalDateTime ldt3 = LocalDateTime.of(2019, 5, 5, 11, 00);
        Auction auction3 = Auction.builder()
                .title("My sons inheritance")
                .description("He told me God was fake and evolution was real so now I'm selling this car I was supposed to give him when he turned 12.")
                .buyOutPrice(Double.parseDouble("20"))
                .startUpPrice(Double.parseDouble("5"))
                .closingTime(Timestamp.valueOf(ldt3).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/3.jpg").build());
        user5.addAuction(auction3);
        auctionService.addAuction(auction3);

        LocalDateTime ldt4 = LocalDateTime.of(2019, 7, 5, 11, 30);
        Auction auction4 = Auction.builder()
                .title("The Love-machine")
                .description("Three of my six children where made in this baby. Now YOU have the chance of owning this love-soaked nightstalker.")
                .buyOutPrice(Double.parseDouble("400"))
                .startUpPrice(Double.parseDouble("25"))
                .closingTime(Timestamp.valueOf(ldt4).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/4.jpg").build())
                .addBid(Bid.builder()
                        .value(55D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(60D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(80D)
                        .user(user4).build())
                .addBid(Bid.builder()
                        .value(100D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(150D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(155D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(200D)
                        .user(user4).build())
                .addBid(Bid.builder()
                        .value(300D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(400D)
                        .user(user4).build())
                .addBid(Bid.builder()
                        .value(401D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(405D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(408D)
                        .user(user4).build())
                .addBid(Bid.builder()
                        .value(500D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(600D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(700D)
                        .user(user4).build())
                .addBid(Bid.builder()
                        .value(800D)
                        .user(user3).build())
                .addBid(Bid.builder()
                        .value(850D)
                        .user(user5).build())
                .addBid(Bid.builder()
                        .value(900D)
                        .user(user4).build());
        user2.addAuction(auction4);
        auctionService.addAuction(auction4);

        LocalDateTime ldt5 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction5 = Auction.builder()
                .title("What's left of my car")
                .description("Took a wrong turn, so it's far from mint condition, but I'm sure theres a few pieces of copper wire that you could grab.")
                .buyOutPrice(Double.parseDouble("1000"))
                .startUpPrice(Double.parseDouble("50"))
                .closingTime(Timestamp.valueOf(ldt5).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/5.jpeg").build());
        user4.addAuction(auction5);
        auctionService.addAuction(auction5);

        LocalDateTime ldt6 = LocalDateTime.of(2019, 5, 20, 11, 00);
        Auction auction6 = Auction.builder()
                .title("2017 Ford Mustang GT500")
                .description("Fun car, toobad it's a Ford. Now I'm selling it to get a real car.")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("1000"))
                .closingTime(Timestamp.valueOf(ldt6).toLocalDateTime())
                .category(categories.get(1))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/1.jpeg").build());
        user3.addAuction(auction6);
        auctionService.addAuction(auction6);

        LocalDateTime ldt7 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction7 = Auction.builder()
                .title("Hipster-bike")
                .description("New in town? Feel like you're not quite cool enough? Get this bike and maybe a slackline or something and you'll be all the rage. Hipsterdom, here you come!")
                .buyOutPrice(Double.parseDouble("1000"))
                .startUpPrice(Double.parseDouble("150"))
                .closingTime(Timestamp.valueOf(ldt7).toLocalDateTime())
                .category(categories.get(4))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/6.jpg").build());
        user2.addAuction(auction7);
        auctionService.addAuction(auction7);

        LocalDateTime ldt8 = LocalDateTime.of(2019, 4, 5, 11, 00);
        Auction auction8 = Auction.builder()
                .title("2012 Cabrinha Switchblade kiteboard")
                .description("Learned ")
                .buyOutPrice(Double.parseDouble("85000"))
                .startUpPrice(Double.parseDouble("15000"))
                .closingTime(Timestamp.valueOf(ldt8).toLocalDateTime())
                .category(categories.get(4))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/7.jpg").build());
        user4.addAuction(auction8);
        auctionService.addAuction(auction8);

        LocalDateTime ldt9 = LocalDateTime.of(2019, 3, 5, 12, 00);
        Auction auction9 = Auction.builder()
                .title("Ugliest cat alive")
                .description("I found him in the trash and took care of him for a while until he tried to kill me in my sleep. Please save me.")
                .buyOutPrice(Double.parseDouble("5"))
                .startUpPrice(Double.parseDouble("1"))
                .closingTime(Timestamp.valueOf(ldt9).toLocalDateTime())
                .category(categories.get(3))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/10.jpg").build());
        user4.addAuction(auction9);
        auctionService.addAuction(auction9);

        LocalDateTime ldt10 = LocalDateTime.of(2019, 7, 1, 12, 00);
        Auction auction10 = Auction.builder()
                .title("2016 K2 Darkstar")
                .description("This is the best board I've ever ridden. Just strap in and fly away.")
                .buyOutPrice(Double.parseDouble("400"))
                .startUpPrice(Double.parseDouble("100"))
                .closingTime(Timestamp.valueOf(ldt10).toLocalDateTime())
                .category(categories.get(4))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/8.jpg").build());
        user4.addAuction(auction10);
        auctionService.addAuction(auction10);

        LocalDateTime ldt11 = LocalDateTime.of(2019, 6, 28, 12, 00);
        Auction auction11 = Auction.builder()
                .title("2010 Macbook")
                .description("It's slow, it's ugly but since it's a Macbook I want at least 200 for it!")
                .buyOutPrice(Double.parseDouble("500"))
                .startUpPrice(Double.parseDouble("200"))
                .closingTime(Timestamp.valueOf(ldt11).toLocalDateTime())
                .category(categories.get(2))
                .status(AuctionStatus.OPEN).build()
                .addImage(Image.builder().filepath("/images/9.jpg").build());
        user2.addAuction(auction11);
        auctionService.addAuction(auction11);
    }
}
