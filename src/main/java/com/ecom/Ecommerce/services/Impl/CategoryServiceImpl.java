package com.ecom.Ecommerce.services.Impl;

import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.entities.Category;
import com.ecom.Ecommerce.payloads.CategoryDto;
import com.ecom.Ecommerce.repo.CategoryRepo;
import com.ecom.Ecommerce.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=new Category();
        BeanUtils.copyProperties(categoryDto,category);
        categoryRepo.save(category);
        BeanUtils.copyProperties(category,categoryDto);
        BeanUtils.copyProperties(category,categoryDto);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(int categoryId, CategoryDto categoryDto) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("category","categoryId",categoryId)
        );
        category.setTitle(categoryDto.getTitle());
        categoryRepo.save(category);
        return categoryDto;
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList=categoryRepo.findAll();
        return categoryList.stream().map(
                category ->
                {
                    CategoryDto categoryDto=new CategoryDto();
                    BeanUtils.copyProperties(category,categoryDto);
                    return categoryDto;
                }
        ).collect(Collectors.toList());

    }

    @Override
    public String deleteCategoryId(int categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("category","categoryId",categoryId)
        );
        categoryRepo.delete(category);
        return "Category Deleted";
    }
}
