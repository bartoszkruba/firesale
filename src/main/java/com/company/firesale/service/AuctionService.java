package com.company.firesale.service;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.repository.AuctionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {

    private AuctionEntityRepository actionEntityRepository;

    @Autowired
    public AuctionService(AuctionEntityRepository actionEntityRepository) {
        this.actionEntityRepository = actionEntityRepository;
    }

    public Optional<Auction> findById(long id) {
        return actionEntityRepository.findById(id);
    }

    public Page<Auction> findTenByDate(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10, Sort.by("closeAt"));
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Iterable<Auction> findFilteredAuctionsOpenWithCategory(String title, Double price, String category, AuctionStatus status){
        return actionEntityRepository.findFilteredAuctionsOpenWithCategory(title, price, category, status);
    }

    public Iterable<Auction> findFilteredAuctionsOpenAllCategories(String title, Double price, AuctionStatus status){
        return actionEntityRepository.findFilteredAuctionsOpenAllCategories(title, price, status);
    }

    public Iterable<Auction> findFilteredAuctionsWithCategory(String title, Double price, String category){
        return actionEntityRepository.findFilteredAuctionsWithCategory(title, price, category);
    }

    public Iterable<Auction> findFilteredAuctionsAllCategories(String title, Double price){
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


}
