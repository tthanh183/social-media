package com.example.socialmedia.repository;

import com.example.socialmedia.entity.InvalidatedToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IInvalidatedTokenRepository extends MongoRepository<InvalidatedToken, String> {
}
