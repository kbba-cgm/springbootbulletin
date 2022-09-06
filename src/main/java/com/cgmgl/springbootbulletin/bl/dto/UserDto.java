package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cgmgl.springbootbulletin.persistence.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    Long id;

    @NotEmpty(message = "Username must not be empty.")
    String name;
  
    @Email(message = "Email must be type of email.")
    @NotEmpty(message = "Email must not be empty.")
    String email;
  
    String position;
    
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    String password;
  
    private Timestamp created_at;
  
    private Timestamp updated_at;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.position = user.getPosition();
        this.password = user.getPassword();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
    }
}
