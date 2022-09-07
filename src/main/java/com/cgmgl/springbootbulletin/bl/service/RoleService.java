package com.cgmgl.springbootbulletin.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.RoleDto;

@Service
public interface RoleService {
    RoleDto findRoleByName(String roleName);

    List<RoleDto> getAllRoles();
}
