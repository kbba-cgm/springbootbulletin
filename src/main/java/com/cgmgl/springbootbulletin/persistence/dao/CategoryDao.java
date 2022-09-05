package com.cgmgl.springbootbulletin.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgmgl.springbootbulletin.persistence.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
    
    
}
