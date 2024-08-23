package com.example.socialmedia.repository;

import com.example.socialmedia.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
