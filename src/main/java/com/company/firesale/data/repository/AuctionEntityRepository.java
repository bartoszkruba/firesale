package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
@Repository
public interface ActionEntityRepository extends CrudRepository<Auction, Long> {
}
*/
@Repository
public interface AuctionEntityRepository extends PagingAndSortingRepository<Auction, Long> {

    Optional<Auction> findById(Long id);

    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.category LIKE :category AND a.status LIKE :status")
    Iterable<Auction> findFilteredAuctionsOpenWithCategory(
            @Param(value="title") String title,
            @Param(value="price") Double price,
            @Param(value="category") String category,
            @Param(value="status") AuctionStatus status);

    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.status LIKE :status")
    Iterable<Auction> findFilteredAuctionsOpenAllCategories(
            @Param(value="title") String title,
            @Param(value="price") Double price,
            @Param(value="status") AuctionStatus status);


    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.category LIKE :category")
    Iterable<Auction> findFilteredAuctionsWithCategory(
            @Param(value="title") String title,
            @Param(value="price") Double price,
            @Param(value="category") String category);

    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price")
    Iterable<Auction> findFilteredAuctionsAllCategories(
            @Param(value="title") String title,
            @Param(value="price") Double price);


}


