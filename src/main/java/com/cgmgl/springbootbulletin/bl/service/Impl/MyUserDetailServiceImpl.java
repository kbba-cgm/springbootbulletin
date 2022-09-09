package com.cgmgl.springbootbulletin.bl.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cgmgl.springbootbulletin.bl.dto.MyUserDetail;
import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.UserService;

@Component
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userService.findUserByEmail(email);

        if(userDto == null)
            throw new UsernameNotFoundException("User does not exist");

        return new MyUserDetail(userDto);
    }
    
}
