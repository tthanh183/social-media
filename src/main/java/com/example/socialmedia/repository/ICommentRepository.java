package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICommentRepository extends MongoRepository<Comment, String> {
    long countByPostId(String postId);
}
