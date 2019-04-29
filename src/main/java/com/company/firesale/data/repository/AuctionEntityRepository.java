package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionEntityRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionById(Long id);
    Page<Auction> findByTitleContaining(String title, Pageable pageable);
    List<Auction> findByTitleContainingAndStartUpPriceIsLessThanEqual(String title, Double buyOutPrice, Pageable pageable);
    List<Auction> findAllByClosingTimeBeforeAndStatus(LocalDateTime closingTime, AuctionStatus status);
}


