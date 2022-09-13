package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cgmgl.springbootbulletin.persistence.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Username must not be empty.")
    private String name;
  
    @Email(message = "Email must be type of email.")
    @NotEmpty(message = "Email must not be empty.")
    private String email;
  
    private String position;
    
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    private String password;

    private RoleDto roleDto;

    private Set<PostDto> posts;
  
    private String photo; // file_path to save in db

    private String photo_handler; // to carry base64 String

    private Timestamp created_at;
  
    private Timestamp updated_at;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.position = user.getPosition();
        this.roleDto = new RoleDto(user.getRole());
        this.password = user.getPassword();
        this.photo = user.getPhoto();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
    }
}
