package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.request.UserCreationRequest;
import com.example.socialmedia.dto.request.UserUpdateRequest;
import com.example.socialmedia.dto.response.UserResponse;
import com.example.socialmedia.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
