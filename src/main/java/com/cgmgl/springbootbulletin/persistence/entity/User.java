package com.cgmgl.springbootbulletin.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.cgmgl.springbootbulletin.bl.dto.UserDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "position")
  private String position;

  @Column(name = "password")
  private String password;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private Timestamp created_at;

  @Column(name = "updated_at")
  private Timestamp updated_at;

  @ManyToOne
  private Role role;

  public User(UserDto userDto) {
    this.id = userDto.getId();
    this.name = userDto.getName();
    this.email = userDto.getEmail();
    this.position = userDto.getPosition();
    this.role = new Role(userDto.getRoleDto());
    this.password = userDto.getPassword();
    this.created_at = userDto.getCreated_at();
    this.updated_at = userDto.getUpdated_at();
  }
}