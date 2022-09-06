package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.UserService;
import com.cgmgl.springbootbulletin.persistence.dao.UserDao;
import com.cgmgl.springbootbulletin.persistence.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDto findUserbyId(long id) {
        return new UserDto(userDao.findById(id).get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> list = new ArrayList<>();
        userDao.findAll().forEach(user -> {
            list.add(new UserDto(user));
        });
        return list;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        User user = new User(userDto);  

        user.setCreated_at(now);
        user.setUpdated_at(now);

        return new UserDto(userDao.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        User user = new User(userDto);  
        
        user.setUpdated_at(now);

        return new UserDto(userDao.save(user));
    }

    @Override
    public void deleteUser(UserDto userDto) {
        userDao.delete(new User(userDto));
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public boolean isUserExistByName(String userName) {
        return userDao.existsByName(userName);
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userDao.existsByName(email);
    }
}
