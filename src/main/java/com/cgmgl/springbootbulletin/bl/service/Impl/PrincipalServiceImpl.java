package com.cgmgl.springbootbulletin.bl.service.Impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.MyUserDetail;
import com.cgmgl.springbootbulletin.bl.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    @Override
    public MyUserDetail getPrincipal() {
        MyUserDetail myUserDetail = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUserDetail;
    }
    
}
