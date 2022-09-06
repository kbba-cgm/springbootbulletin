package com.cgmgl.springbootbulletin.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.cgmgl.springbootbulletin.bl.dto.CategoryDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;
    
    public Category(CategoryDto categoryDto) {
        id = categoryDto.getId();
        name = categoryDto.getName();
        created_at = categoryDto.getCreated_at();
        updated_at = categoryDto.getUpdated_at();
    }
}
