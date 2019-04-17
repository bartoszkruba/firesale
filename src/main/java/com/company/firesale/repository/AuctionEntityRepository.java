package com.company.firesale.repository;

import com.company.firesale.entity.Auction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
@Repository
public interface ActionEntityRepository extends CrudRepository<Auction, Long> {
}
*/
@Repository
public interface AuctionEntityRepository extends PagingAndSortingRepository<Auction, Long> {

    Optional<Auction> findById(Long id);

}


