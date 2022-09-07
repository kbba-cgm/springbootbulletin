package com.cgmgl.springbootbulletin.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.PostDto;

@Service
public interface PostService {
    PostDto findPostbyId(long id);

    List<PostDto> getAllPosts();

    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto);

    void deletePost(PostDto postDto);

    void deletePostById(Long id);

    boolean isPostExistById(Long id);
}
