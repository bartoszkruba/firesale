package com.company.firesale.service;

import com.company.firesale.data.entity.*;
import com.company.firesale.data.repository.AuctionEntityRepository;
import com.company.firesale.json_classes.AuctionFormJsonClass;
import com.company.firesale.json_classes.AuctionJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public AuctionJsonClass findById(long id) {
        return new AuctionJsonClass(actionEntityRepository.findAuctionById(id));
    }

    public List<AuctionJsonClass> findTenByTitle(String title, Integer page) {
        Pageable pageWithTen = PageRequest.of(page, 5, Sort.by("closingTime"));
        List<AuctionJsonClass> auctions = new ArrayList<>();
        actionEntityRepository.findByTitleContaining(title, pageWithTen).forEach(a -> auctions.add(new AuctionJsonClass(a)));
        return auctions;
    }

//    public List<Auction> findTenByTitleAndBuyoutPrice(String title, Double price, Integer page) {
//        Pageable pageWithTen = PageRequest.of(page, 5, Sort.by("closingTime"));
//        return actionEntityRepository.findByTitleContainingAndStartUpPriceIsLessThanEqual(title, price, pageWithTen);
//    }

    public Page<Auction> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closingTime"));
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

    public ResponseEntity<AuctionJsonClass> createNewAuction(AuctionFormJsonClass auction, String username) {
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
            DBAuction.setCategory(category);

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

            return new ResponseEntity<>(new AuctionJsonClass(DBAuction), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public AuctionJsonClass getAuctionById(Long id) {
        Optional<Auction> a = actionEntityRepository.findById(id);
        if (a.isPresent()) {
            return new AuctionJsonClass(a.get());
        } else {
            return null;
        }
    }

    private boolean validateAuctionForm(AuctionFormJsonClass auction) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (auction.getClosingTime().isBefore(currentTime)) {
            return false;
        } else if (auction.getStartUpPrice() < 0) {
            return false;
        } else if (auction.getBuyOutPrice() <= auction.getStartUpPrice()) {
            return false;
        } else {
            return true;
        }
    }

}
