package com.cgmgl.springbootbulletin.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgmgl.springbootbulletin.persistence.entity.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
    boolean existsByName(String name);
 }
