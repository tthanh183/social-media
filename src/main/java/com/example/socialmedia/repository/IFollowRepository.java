package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFollowRepository extends MongoRepository<Follow, String> {
    Follow findByFollowerIdAndFolloweeId(String followerId, String followeeId);
}
