package com.example.socialmedia.service;

import com.example.socialmedia.dto.request.FollowRequest;
import com.example.socialmedia.dto.response.FollowResponse;

public interface IFollowService {
    public FollowResponse follow(FollowRequest request);
}
