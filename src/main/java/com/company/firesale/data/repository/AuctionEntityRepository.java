package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Auction;
import com.company.firesale.data.entity.AuctionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository
public interface ActionEntityRepository extends CrudRepository<Auction, Long> {
}
*/
@Repository
public interface AuctionEntityRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionById(Long id);

    //    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%')")
//    List<Auction> findAuctionsByTitle(String title);
    List<Auction> findByTitleContaining(String title, Pageable pageable);
    List<Auction> findByTitleContainingAndBuyOutPriceLessThanEqual(String title, Double buyOutPrice, Pageable pageable);
    List<Auction> findByTitleContainingAndStartUpPriceIsLessThanEqual(String title, Double buyOutPrice, Pageable pageable);



    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.category LIKE :category AND a.status LIKE :status")
    Iterable<Auction> findFilteredAuctionsOpenWithCategory(
            @Param(value = "title") String title,
            @Param(value = "price") Double price,
            @Param(value = "category") String category,
            @Param(value = "status") AuctionStatus status);


    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.status LIKE :status")
    Iterable<Auction> findFilteredAuctionsOpenAllCategories(
            @Param(value = "title") String title,
            @Param(value = "price") Double price,
            @Param(value = "status") AuctionStatus status);


    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price AND a.category LIKE :category")
    Iterable<Auction> findFilteredAuctionsWithCategory(
            @Param(value = "title") String title,
            @Param(value = "price") Double price,
            @Param(value = "category") String category);

    //TODO: boy_out_price should be current highest bid
    @Query(value = "SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%') AND a.buyOutPrice <= :price")
    Iterable<Auction> findFilteredAuctionsAllCategories(
            @Param(value = "title") String title,
            @Param(value = "price") Double price);


}


