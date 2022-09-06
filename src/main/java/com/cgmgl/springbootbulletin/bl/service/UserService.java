package com.cgmgl.springbootbulletin.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.UserDto;

@Service
public interface UserService {
    UserDto findUserbyId(long id);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);

    void deleteUserById(Long id);

    boolean isUserExistByName(String userName);

    boolean isUserExistByEmail(String email);
}
