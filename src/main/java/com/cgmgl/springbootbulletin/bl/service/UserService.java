package com.cgmgl.springbootbulletin.bl.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.UserDto;

@Service
public interface UserService {
    UserDto findUserbyId(long id);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto) throws FileNotFoundException, IOException;

    UserDto updateUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, ServletRequest request) throws FileNotFoundException, IOException;

    void deleteUser(UserDto userDto);

    void deleteUserById(Long id);

    boolean isUserExistByName(String userName);

    boolean isUserExistByEmail(String email);

    UserDto findUserByEmail(String email);
}
