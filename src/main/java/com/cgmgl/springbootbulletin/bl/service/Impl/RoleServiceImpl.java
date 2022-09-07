package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.RoleDto;
import com.cgmgl.springbootbulletin.bl.service.RoleService;
import com.cgmgl.springbootbulletin.persistence.dao.RoleDao;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public RoleDto findRoleByName(String roleName) {
        return new RoleDto(roleDao.findByName(roleName));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleDto> list = new ArrayList<>();
        roleDao.findAll().forEach(role -> {
            list.add(new RoleDto(role));
        });
        return list;
    }
}
