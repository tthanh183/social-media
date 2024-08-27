package com.example.socialmedia.controller;

import com.example.socialmedia.dto.request.FollowRequest;
import com.example.socialmedia.dto.response.ApiResponse;
import com.example.socialmedia.dto.response.FollowResponse;
import com.example.socialmedia.service.IFollowService;
import com.example.socialmedia.service.impl.FollowService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowController {
    IFollowService followService;

    @PostMapping("")
    public ApiResponse<FollowResponse> follow(@RequestBody FollowRequest request) {
        return ApiResponse.<FollowResponse>builder().result(followService.follow(request)).build();
    }
}
