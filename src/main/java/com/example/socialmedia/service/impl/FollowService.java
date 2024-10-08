package com.example.socialmedia.service.impl;

import com.example.socialmedia.dto.request.FollowRequest;
import com.example.socialmedia.dto.response.FollowResponse;
import com.example.socialmedia.entity.Follow;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.AppException;
import com.example.socialmedia.exception.ErrorCode;
import com.example.socialmedia.repository.IFollowRepository;
import com.example.socialmedia.repository.IUserRepository;
import com.example.socialmedia.service.IFollowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowService implements IFollowService {
    IFollowRepository followRepository;
    IUserRepository userRepository;

    @Override
    public FollowResponse follow(FollowRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Follow follow = followRepository.findByFollowerIdAndFolloweeId(user.getId(), request.getFolloweeId());
        String status = "";
        if (follow != null) {
            followRepository.deleteById(follow.getId());
            status = "Unfollow successfully";
        }else {
            followRepository.save(Follow.builder().followerId(user.getId()).followeeId(request.getFolloweeId()).build());
            status = "Follow successfully";
        }
        return FollowResponse.builder().status(status).build();
    }

}
