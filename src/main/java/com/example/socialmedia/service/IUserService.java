package com.example.socialmedia.service;

import com.example.socialmedia.dto.request.UserCreationRequest;
import com.example.socialmedia.dto.request.UserUpdateRequest;
import com.example.socialmedia.dto.response.UserResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUserService {
    public UserResponse createUser(UserCreationRequest request);
    public UserResponse updateUser(String id, UserUpdateRequest request);
    public void deleteUser(String id);
    public UserResponse getMyInfo();
}
