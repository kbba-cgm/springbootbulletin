package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.PostDto;
import com.cgmgl.springbootbulletin.bl.service.PostService;
import com.cgmgl.springbootbulletin.persistence.dao.PostDao;
import com.cgmgl.springbootbulletin.persistence.entity.Post;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Override
    public PostDto findPostbyId(long id) {
        return new PostDto(postDao.findById(id).get());
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> list = new ArrayList<>();
        postDao.findAll().forEach(post -> {
            list.add(new PostDto(post));
        });
        return list;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        Post post = new Post(postDto);
        post.setCreated_at(now);
        post.setUpdated_at(now);

        return new PostDto(postDao.save(post));
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        Post post = new Post(postDto);
        post.setUpdated_at(now);

        return new PostDto(postDao.save(post));
    }

    @Override
    public void deletePost(PostDto postDto) {
        postDao.delete(new Post(postDto));
    }

    @Override
    public void deletePostById(Long id) {
        postDao.deleteById(id);
    }

    @Override
    public boolean isPostExistById(Long id) {
        return postDao.existsById(id);
    }
    
}
