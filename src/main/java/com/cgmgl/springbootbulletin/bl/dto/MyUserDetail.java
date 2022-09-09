package com.cgmgl.springbootbulletin.bl.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail implements UserDetails {
    private long id;
    private String name;
    private String email;
    private String password;
    private String position;
    private RoleDto roleDto;

    public MyUserDetail(UserDto userDto) {
        id = userDto.getId();
        name = userDto.getName();
        email = userDto.getEmail();
        position = userDto.getPosition();
        password = userDto.getPassword();
        roleDto = userDto.getRoleDto();
    }

    public void updatePrincipal(UserDto userDto) {
        id = userDto.getId();
        name = userDto.getName();
        email = userDto.getEmail();
        position = userDto.getPosition();
        password = userDto.getPassword();
        roleDto = userDto.getRoleDto();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(roleDto.getName()));
		return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
