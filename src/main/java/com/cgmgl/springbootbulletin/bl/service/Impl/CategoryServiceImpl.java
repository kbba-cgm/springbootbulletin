package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.CategoryDto;
import com.cgmgl.springbootbulletin.bl.service.CategoryService;
import com.cgmgl.springbootbulletin.persistence.dao.CategoryDao;
import com.cgmgl.springbootbulletin.persistence.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public CategoryDto findCategorybyId(long id) {
        return new CategoryDto(categoryDao.findById(id).get());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> list = new ArrayList<>();
        categoryDao.findAll().forEach(category -> {
            list.add(new CategoryDto(category));
        });
        return list;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        Category category = new Category(categoryDto);
        category.setCreated_at(now);
        category.setUpdated_at(now);

        return new CategoryDto(categoryDao.save(category));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        Category category = new Category(categoryDto);
        category.setUpdated_at(now);

        return new CategoryDto(categoryDao.save(category));
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) {
        categoryDao.delete(new Category(categoryDto));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryDao.deleteById(id);
    }

    @Override
    public boolean isCategoryExistByName(String categoryName) {
        return categoryDao.existsByName(categoryName);
    }
    
}
