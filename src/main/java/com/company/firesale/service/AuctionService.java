package com.company.firesale.service;

import com.company.firesale.entity.Auction;
import com.company.firesale.repository.AuctionEntityRepository;
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

    public Page<Auction> findTenById(int page) {
        Pageable PageWithTen = PageRequest.of(page, 10);
        return actionEntityRepository.findAll(PageWithTen);
    }

    public Auction createActionEntity() {
        Auction actionEntity = new Auction();
        actionEntityRepository.save(actionEntity);
        return actionEntity;
    }


}
