package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import com.cgmgl.springbootbulletin.persistence.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private long id;

    @NotEmpty(message =  "Category name must not be empty.")
    private String name;

    private Timestamp created_at;
    private Timestamp updated_at;

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
        created_at = category.getCreated_at();
        updated_at = category.getUpdated_at();
    }
    
}
