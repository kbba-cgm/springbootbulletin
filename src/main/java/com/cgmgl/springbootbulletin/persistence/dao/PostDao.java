package com.cgmgl.springbootbulletin.persistence.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cgmgl.springbootbulletin.persistence.entity.Post;

@Repository
public interface PostDao extends CrudRepository<Post, Long> {
    boolean existsById(Long id);

    List<Post> findByPublishedTrue();

    @Query("from Post p where p.user.id = :user_id")
    List<Post> findPostsByUserId(@Param("user_id") Long user_id);
}
