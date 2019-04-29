package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import com.company.firesale.data.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionEntityRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionById(Long id);
    Page<Auction> findByTitleContaining(String title, Pageable pageable);
    Page<Auction> findByTitleContainingAndStatus(String title, AuctionStatus status, Pageable pageable);
    Page<Auction> findByTitleContainingAndCategoryLikeAndStatus(String title, Category category, AuctionStatus status, Pageable pageable);
    Page<Auction> findByTitleContainingAndCategoryLike(String title, Category category, Pageable pageable);
    List<Auction> findAllByClosingTimeBeforeAndStatus(LocalDateTime closingTime, AuctionStatus status);
}


