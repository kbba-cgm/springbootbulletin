package com.cgmgl.springbootbulletin.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.CategoryDto;

@Service
public interface CategoryService {
    CategoryDto findCategorybyId(long id);

    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long id);

    boolean isCategoryExistByName(String categoryName);
}
