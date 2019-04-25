package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
@Repository
public interface ActionEntityRepository extends CrudRepository<Auction, Long> {
}
*/
@Repository
public interface AuctionEntityRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionById(Long id);

    List<Auction> findByTitleContaining(String title, Pageable pageable);
    List<Auction> findByTitleContainingAndStartUpPriceIsLessThanEqual(String title, Double buyOutPrice, Pageable pageable);

}


