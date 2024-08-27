package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILikeRepository extends MongoRepository<Like,String> {
    long countByTargetId(String targetId);
}
