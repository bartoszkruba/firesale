package com.company.firesale.data.repository;

import com.company.firesale.data.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Iterable<Category> findAll();

    Category findByName(String name);
}
