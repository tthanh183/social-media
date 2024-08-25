package com.example.socialmedia.controller;

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
    public void follow(@RequestBody String followeeId) {
        followService.follow(followeeId);
    }
}
