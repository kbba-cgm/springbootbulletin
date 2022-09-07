package com.cgmgl.springbootbulletin.bl.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import com.cgmgl.springbootbulletin.persistence.entity.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    Long id;

    @NotEmpty(message = "Title must not be empty.")
    String title;

    @NotEmpty(message = "Content must not be empty.")
    String content;

    private Timestamp created_at;

    private Timestamp updated_at;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.created_at = post.getCreated_at();
        this.updated_at = post.getUpdated_at();
    }
}
