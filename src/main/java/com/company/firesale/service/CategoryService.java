package com.company.firesale.service;

import com.company.firesale.data.entity.Category;
import com.company.firesale.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable findAll() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
