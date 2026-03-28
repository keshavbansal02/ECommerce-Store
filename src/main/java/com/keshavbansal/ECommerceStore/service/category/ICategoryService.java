package com.keshavbansal.ECommerceStore.service.category;

import com.keshavbansal.ECommerceStore.model.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryByName(String name);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category,Long id);

    void deleteCategory(Long id);

}
