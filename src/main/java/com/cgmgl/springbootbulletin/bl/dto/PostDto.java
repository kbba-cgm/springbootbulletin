package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.cgmgl.springbootbulletin.persistence.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Long id;

    @NotEmpty(message = "Title must not be empty.")
    private String title;

    @NotEmpty(message = "Content must not be empty.")
    private String content;

    @Valid
    private UserDto userDto;

    @Valid
    private Set<CategoryDto> categoryDtos = new HashSet<>();

    private boolean published = false;

    private Timestamp created_at;

    private Timestamp updated_at;

    private long[] categoryIds;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userDto = new UserDto(post.getUser());
        this.published = post.isPublished();
        this.created_at = post.getCreated_at();
        this.updated_at = post.getUpdated_at();
    }
}
