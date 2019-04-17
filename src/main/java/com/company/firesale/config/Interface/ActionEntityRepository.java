package com.company.firesale.config.Interface;

import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/*
@Repository
public interface ActionEntityRepository extends CrudRepository<ActionEntity, Long> {
}
*/
@Repository
public interface ActionEntityRepository extends PagingAndSortingRepository<ActionEntity, Date> {

    //List<ActionEntity> findTenByCloseAt(Date closeat, Pageable pageable);

}


