package com.cgmgl.springbootbulletin.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgmgl.springbootbulletin.persistence.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
