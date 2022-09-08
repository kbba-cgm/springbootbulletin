package com.cgmgl.springbootbulletin.bl.dto;

import java.util.Set;

import com.cgmgl.springbootbulletin.persistence.entity.Role;
import com.cgmgl.springbootbulletin.persistence.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
    private Long id;

    private String name;

    private Set<User> users;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.users = role.getUsers();
    }
}
