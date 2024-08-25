package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
}
