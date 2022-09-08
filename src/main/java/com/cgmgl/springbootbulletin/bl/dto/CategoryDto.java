package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.cgmgl.springbootbulletin.persistence.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private long id;

    @NotEmpty(message =  "Category name must not be empty.")
    private String name;

    private Set<PostDto> postDtos = new HashSet<PostDto>();

    private Timestamp created_at;
    private Timestamp updated_at;

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
        created_at = category.getCreated_at();
        updated_at = category.getUpdated_at();
    }    
}
