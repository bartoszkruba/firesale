package com.company.firesale.service;

import com.company.firesale.data.entity.Image;
import com.company.firesale.data.entity.User;
import com.company.firesale.entity.Auction;
import com.company.firesale.json_classes.TestAuctionJsonClass;
import com.company.firesale.repository.AuctionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuctionService {

    private final AuctionEntityRepository actionEntityRepository;

    private final UserService userService;

    private final ImageService imageService;

    @Autowired
    public AuctionService(AuctionEntityRepository actionEntityRepository, UserService userService, ImageService imageService) {
        this.actionEntityRepository = actionEntityRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    public Optional<Auction> findById(long id) {
        return actionEntityRepository.findById(id);
    }

    public Page<Auction> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closeAt"));
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Page<Auction> findTenById(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10);
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Auction createActionEntity() {
        Auction actionEntity = new Auction();
        actionEntityRepository.save(actionEntity);
        return actionEntity;
    }

    // TODO: 2019-04-22 should return JsonAuction
    public Auction createNewAuction(TestAuctionJsonClass auction, String username) {
        Auction DBAuction = new Auction();

        // TODO: 2019-04-22 Check if user isn't null
        User user = userService.getUserByUsername(username);
        user.addAuction(DBAuction);
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

        return DBAuction;
    }

}
