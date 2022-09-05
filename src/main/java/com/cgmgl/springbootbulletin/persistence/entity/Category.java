package com.cgmgl.springbootbulletin.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Category {
    private long id;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    
}
