package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid>findByAuction_IdOrderByValueDesc(Long id);
}
