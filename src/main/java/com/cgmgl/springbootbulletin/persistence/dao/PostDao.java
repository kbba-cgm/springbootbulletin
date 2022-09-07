package com.cgmgl.springbootbulletin.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgmgl.springbootbulletin.persistence.entity.Post;

@Repository
public interface PostDao extends CrudRepository<Post, Long> {
    boolean existsById(Long id);
}
