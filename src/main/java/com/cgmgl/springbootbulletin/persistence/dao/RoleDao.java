package com.cgmgl.springbootbulletin.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cgmgl.springbootbulletin.persistence.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(String roleName);
}
