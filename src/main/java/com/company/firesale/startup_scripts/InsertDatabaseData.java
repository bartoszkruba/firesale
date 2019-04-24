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
        User user1 = new User();
        user1.setUsername("john69")
                .setPassword(encoder.encode("password1234"))
                .setEmail("john.doe@gmail.com")
                .setAddress("4323 Oak Lawn Ave")
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("(127)-963-1879")
                .addRole(adminRole)
                .addRole(userRole);

        userService.saveUser(user1);

        User user2 = new User();
        user2.setUsername("mary111")
                .setPassword(encoder.encode("mypassword"))
                .setEmail("mary323@gmail.com")
                .setAddress("4968 Railroad St")
                .setFirstName("Mary")
                .setLastName("Poppins")
                .setPhoneNumber("(277)-505-2351")
                .addRole(userRole);

        userService.saveUser(user2);

        User user3 = new User();
        user3.setUsername("ChrisL")
                .setPassword(encoder.encode("wyatt"))
                .setEmail("chris.lewis92@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Chris")
                .setLastName("Lewis")
                .setPhoneNumber("(893)-121-7525")
                .addRole(userRole);
        userService.saveUser(user3);

        User user4 = new User();
        user4.setUsername("Morenorator")
                .setPassword(encoder.encode("reptile"))
                .setEmail("ruben.moreno87@example.com")
                .setAddress("5860 Camden Ave")
                .setFirstName("Ruben")
                .setLastName("Moreno")
                .setPhoneNumber("(247)-425-7475")
                .addRole(userRole);
        userService.saveUser(user4);

        User user5 = new User();
        user5.setUsername("Cindirella")
                .setPassword(encoder.encode("poopoo"))
                .setEmail("ruben.moreno87@example.com")
                .setAddress("1164 Adelaide Ave")
                .setFirstName("Cindy")
                .setLastName("Stephens")
                .setPhoneNumber("(272)-121-1765")
                .addRole(userRole);
        userService.saveUser(user5);

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
        bid1.setYourBid(Double.parseDouble("20000"))
                .setAuction(auction1)
                .setUser(user2);
        bidRepository.save(bid1);

        Bid bid2 = new Bid();
        bid2.setYourBid(Double.parseDouble("250"))
                .setAuction(auction4)
                .setUser(user3);
        bidRepository.save(bid2);

        Bid bid3 = new Bid();
        bid3.setYourBid(Double.parseDouble("300"))
                .setAuction(auction4)
                .setUser(user5);
        bidRepository.save(bid3);

        Bid bid4 = new Bid();
        bid4.setYourBid(Double.parseDouble("400"))
                .setAuction(auction4)
                .setUser(user1);
        bidRepository.save(bid4);


    }
}
