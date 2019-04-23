package com.company.firesale.service;

import com.company.firesale.data.entity.*;
import com.company.firesale.data.repository.AuctionEntityRepository;
import com.company.firesale.json_classes.AuctionFormJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuctionService {

    private final AuctionEntityRepository actionEntityRepository;

    private final UserService userService;

    private final ImageService imageService;

    private final CategoryService categoryService;

    @Autowired
    public AuctionService(AuctionEntityRepository actionEntityRepository, UserService userService, ImageService imageService, CategoryService categoryService) {
        this.actionEntityRepository = actionEntityRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.categoryService = categoryService;
    }

    public Optional<Auction> findById(long id) {
        return actionEntityRepository.findById(id);
    }

    public Page<Auction> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closeAt"));
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Iterable<Auction> findFilteredAuctionsOpenWithCategory(String title, Double price, String category, AuctionStatus status) {
        return actionEntityRepository.findFilteredAuctionsOpenWithCategory(title, price, category, status);
    }

    public Iterable<Auction> findFilteredAuctionsOpenAllCategories(String title, Double price, AuctionStatus status) {
        return actionEntityRepository.findFilteredAuctionsOpenAllCategories(title, price, status);
    }

    public Iterable<Auction> findFilteredAuctionsWithCategory(String title, Double price, String category) {
        return actionEntityRepository.findFilteredAuctionsWithCategory(title, price, category);
    }

    public Iterable<Auction> findFilteredAuctionsAllCategories(String title, Double price) {
        return actionEntityRepository.findFilteredAuctionsAllCategories(title, price);
    }

    public Page<Auction> findTenById(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10);
        return actionEntityRepository.findAll(PageWithTen);
    }

    public void addAuction(Auction auction) {
        try {
            actionEntityRepository.save(auction);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public Auction createActionEntity() {
        Auction actionEntity = new Auction();
        actionEntityRepository.save(actionEntity);
        return actionEntity;
    }


    // TODO: 2019-04-22 should return JsonAuction
    public ResponseEntity<Auction> createNewAuction(AuctionFormJsonClass auction, String username) {
        User user = userService.getUserByUsername(username);
        Category category = categoryService.findCategoryByName(auction.getCategory());
        if (validateAuctionForm(auction) && user != null && category != null) {
            Auction DBAuction = new Auction();
            user.addAuction(DBAuction);
            DBAuction.setTitle(auction.getTitle());
            DBAuction.setDescription(auction.getDescription());
            DBAuction.setBuyOutPrice(auction.getBuyOutPrice());
            DBAuction.setStartUpPrice(auction.getStartUpPrice());
            DBAuction.setClosingTime(auction.getClosingTime());
            DBAuction.setStatus(AuctionStatus.OPEN);

            // TODO: 2019-04-23 Add Category to auction
//        DBAuction.setCategory(category);

            Arrays.stream(auction.getImages()).forEach(i -> {
                try {
                    Image image = imageService.uploadImage(i);
                    DBAuction.addImage(image);
                } catch (Exception e) {
                    System.out.println("Couldn't save image: " + e.getMessage());
                }
            });
            userService.saveUser(user);
            actionEntityRepository.save(DBAuction);

            return new ResponseEntity<>(DBAuction, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // TODO: 2019-04-23 Validate everything
    private boolean validateAuctionForm(AuctionFormJsonClass auction) {
        return true;
    }

}
