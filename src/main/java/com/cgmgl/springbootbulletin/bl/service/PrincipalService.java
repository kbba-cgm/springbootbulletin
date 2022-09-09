package com.cgmgl.springbootbulletin.bl.service;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.MyUserDetail;

@Service
public interface PrincipalService {
    MyUserDetail getPrincipal();

    boolean isAdmin();
}
