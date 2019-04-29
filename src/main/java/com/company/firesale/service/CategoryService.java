package com.company.firesale.service;

import com.company.firesale.data.entity.Category;
import com.company.firesale.data.repository.CategoryRepository;
import com.company.firesale.json_classes.CategoryJsonClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<CategoryJsonClass> findAllTransferIntoJsonClass() {
        Set<CategoryJsonClass> categories = new HashSet<>();
        categoryRepository.findAll().forEach(c -> categories.add(new CategoryJsonClass(c)));
        return categories;
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByNameLike(name);
    }


}
