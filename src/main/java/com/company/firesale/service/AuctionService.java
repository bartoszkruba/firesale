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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

//    public PageJSONAuctions findFiveByTitle(String title, Integer page) {
//        Pageable pageWithFive = PageRequest.of(page, 5, Sort.by("closingTime"));
//        Set<AuctionJsonClass> auctions = new HashSet<>();
//        Page pageWithAuctions = actionEntityRepository.findByTitleContaining(title, pageWithFive);
//        pageWithAuctions.forEach(a -> auctions.add(new AuctionJsonClass((Auction) a)));
//        return PageJSONAuctions.builder().currentPage(pageWithAuctions.getNumber()).totalPages(pageWithAuctions.getTotalPages()).list(auctions).build();
//    }

    public PageJSONAuctions findFiveByTitleAndStatus(String title, String cat, Boolean showAll, Integer page) {
        Pageable pageWithFive = PageRequest.of(page, 5, Sort.by("closingTime"));
        Set<AuctionJsonClass> auctions = new HashSet<>();
        Page pageWithAuctions;
        if(showAll){
            if(cat.equals("All")){
                pageWithAuctions = actionEntityRepository.findByTitleContaining(title, pageWithFive);
            } else {
                Category category = categoryService.findCategoryByName(cat);
                pageWithAuctions = actionEntityRepository.findByTitleContainingAndCategoryLike(title, category, pageWithFive);
            }
        } else {
            if(cat.equals("All")){
                pageWithAuctions = actionEntityRepository.findByTitleContainingAndStatus(title, AuctionStatus.OPEN, pageWithFive);
            } else {
                Category category = categoryService.findCategoryByName(cat);
                pageWithAuctions = actionEntityRepository.findByTitleContainingAndCategoryLikeAndStatus(title, category, AuctionStatus.OPEN, pageWithFive);
            }
        }
        pageWithAuctions.forEach(a -> auctions.add(new AuctionJsonClass((Auction) a)));
        return PageJSONAuctions.builder().currentPage(pageWithAuctions.getNumber()).totalPages(pageWithAuctions.getTotalPages()).list(auctions).build();
    }

    public Page<Auction> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closingTime"));
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

    public ResponseEntity<AuctionJsonClass> createNewAuction(AuctionFormJsonClass auction, String username) {
        User user = userService.getUserByUsername(username);
        Category category = categoryService.findCategoryByName(auction.getCategory());

        if (validateBidForm(auction) && user != null && category != null) {
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
            actionEntityRepository.save(DBAuction);

            return new ResponseEntity<>(new AuctionJsonClass(DBAuction), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public AuctionJsonClass getAuctionJsonClassById(Long id) {
        Optional<Auction> a = actionEntityRepository.findById(id);
        if (a.isPresent()) {
            return new AuctionJsonClass(a.get());
        } else {
            return null;
        }
    }

    public Auction getAuctionById(Long id){
        return actionEntityRepository.getOne(id);
    }

    private boolean validateBidForm(AuctionFormJsonClass auction) {
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
    /*public List<Bid> getAuctionsByUserName(){// eller id ///TODO

        return
    }*/

}
