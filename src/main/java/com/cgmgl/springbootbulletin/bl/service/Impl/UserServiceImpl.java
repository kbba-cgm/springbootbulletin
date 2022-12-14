package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.RoleDto;
import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.UserService;
import com.cgmgl.springbootbulletin.helper.PhotoUploadHelper;
import com.cgmgl.springbootbulletin.persistence.dao.RoleDao;
import com.cgmgl.springbootbulletin.persistence.dao.UserDao;
import com.cgmgl.springbootbulletin.persistence.entity.Role;
import com.cgmgl.springbootbulletin.persistence.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PrincipalServiceImpl principalServiceImpl;

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
    public UserDto createUser(UserDto userDto) throws FileNotFoundException, IOException {
        Timestamp now = new Timestamp(new Date().getTime());
        RoleDto roleDto = getDefaultRole();

        if(userDto.getRoleDto() != null)
            roleDto = getRoleForUserById(userDto.getRoleDto().getId()); 

        userDto.setRoleDto(roleDto);

        // save image file
        PhotoUploadHelper.writeImageData(userDto.getPhoto_handler(), userDto.getPhoto());

        User user = new User(userDto);

        if(user.getPassword() != null)
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));;

        user.setCreated_at(now);
        user.setUpdated_at(now);

        return new UserDto(userDao.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if(!validatedUserId(userDto))
            return null;

        Timestamp now = new Timestamp(new Date().getTime());
        RoleDto roleDto = getDefaultRole();

        if(principalServiceImpl.isAdmin())
            roleDto = getRoleForUserById(1L); 
            
        userDto.setRoleDto(roleDto);

        User user = new User(userDto);   
        if(user.getPassword() != null)
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));;
            
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

    private RoleDto getRoleForUserById(long id) {
        var roleOptional = roleDao.findById(id);
        if(!roleOptional.isPresent())
            return getDefaultRole();

        Role role = roleOptional.get();
        return new RoleDto(role);
    }

    private RoleDto getDefaultRole() {
        return new RoleDto(roleDao.findById(2L).get());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return new UserDto(userDao.findByEmail(email));
    }

    private boolean validatedUserId(UserDto userDto) {
        if(userDto.getId() != principalServiceImpl.getPrincipal().getId())
            return false;
        
        return true;
    }

    @Override
    public UserDto updateUser(UserDto userDto, ServletRequest request) throws FileNotFoundException, IOException {
       
        return null;
    }
}
