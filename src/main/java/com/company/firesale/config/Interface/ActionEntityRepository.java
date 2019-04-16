package com.company.firesale.config.Interface;

import com.company.firesale.config.Tabeller.ActionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionEntityRepository extends CrudRepository<ActionEntity, Long> {
}
