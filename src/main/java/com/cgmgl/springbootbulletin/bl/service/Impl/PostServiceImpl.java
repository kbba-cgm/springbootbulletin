package com.cgmgl.springbootbulletin.bl.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgmgl.springbootbulletin.bl.dto.CategoryDto;
import com.cgmgl.springbootbulletin.bl.dto.PostDto;
import com.cgmgl.springbootbulletin.bl.dto.UserDto;
import com.cgmgl.springbootbulletin.bl.service.PostService;
import com.cgmgl.springbootbulletin.persistence.dao.CategoryDao;
import com.cgmgl.springbootbulletin.persistence.dao.PostDao;
import com.cgmgl.springbootbulletin.persistence.dao.UserDao;
import com.cgmgl.springbootbulletin.persistence.entity.Category;
import com.cgmgl.springbootbulletin.persistence.entity.Post;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PostDto findPostbyId(long id) {
        var post = postDao.findById(id).get();
        var postDto = new PostDto(post);
        
        post.getCategories().forEach(c -> {
            postDto.getCategoryDtos().add(new CategoryDto(c));
        });

        return postDto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> list = new ArrayList<>();
        postDao.findAll().forEach(post -> {
            PostDto postDto = new PostDto(post);
            post.getCategories().forEach(c -> postDto.getCategoryDtos().add(new CategoryDto(c)));
            list.add(postDto);
        });
        return list;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Timestamp now = new Timestamp(new Date().getTime());

        postDto.setUserDto(getAuthor());
        Set<CategoryDto> categoryDtos = getCategoriesFromIds(postDto.getCategoryIds());
        postDto.setCategoryDtos(categoryDtos);

        Post post = new Post(postDto);
        postDto.getCategoryDtos().forEach(categoryDto -> post.getCategories().add(new Category(categoryDto)));
    
        post.setCreated_at(now);
        post.setUpdated_at(now);

        return new PostDto(postDao.save(post));
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Timestamp now = new Timestamp(new Date().getTime());
        postDto.setUserDto(getAuthor());
        Set<CategoryDto> categoryDtos = getCategoriesFromIds(postDto.getCategoryIds());

        postDto.setCategoryDtos(categoryDtos);
        Post post = new Post(postDto);
        postDto.getCategoryDtos().forEach(categoryDto -> post.getCategories().add(new Category(categoryDto)));
    
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
 
    private Set<CategoryDto>  getCategoriesFromIds(long[] categoryIds) {
        LongStream stream = Arrays.stream(categoryIds);
        return stream.mapToObj(categoryId -> new CategoryDto(categoryDao.findById(categoryId).get())).collect(Collectors.toSet());
    }

    private UserDto getAuthor() {
        return new UserDto(userDao.findById(1L).get());
    }
}
