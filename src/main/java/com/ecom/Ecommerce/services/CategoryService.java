package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto addCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(int categoryId,CategoryDto categoryDto);
    public List<CategoryDto> getAllCategories();
    public String deleteCategoryId(int categoryId);

}
