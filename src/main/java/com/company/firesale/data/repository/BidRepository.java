package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Bid;
import com.company.firesale.json_classes.BidJsonClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    //List<Bid>findByAuction_IdOrderByValueDesc(Long id);

    Page<Bid>findByAuction_IdOrderByValueDesc(Long id, Pageable pageable);

}
