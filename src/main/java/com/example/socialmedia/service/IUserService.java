package com.example.socialmedia.service;

import com.example.socialmedia.dto.request.UserCreationRequest;
import com.example.socialmedia.dto.request.UserUpdateRequest;
import com.example.socialmedia.dto.response.UserResponse;
import com.example.socialmedia.entity.User;

public interface IUserService {
    public UserResponse createUser(UserCreationRequest request);
    public UserResponse updateUser(String userId, UserUpdateRequest request);
    public void deleteUser(String userId);
    public UserResponse getMyInfo();
    public User getContextUser();
}
