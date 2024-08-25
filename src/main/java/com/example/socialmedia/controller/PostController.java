package com.example.socialmedia.controller;

import com.example.socialmedia.dto.request.PostCreationRequest;
import com.example.socialmedia.dto.request.PostUpdateRequest;
import com.example.socialmedia.dto.response.ApiResponse;
import com.example.socialmedia.dto.response.PostResponse;
import com.example.socialmedia.service.IPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    IPostService postService;

    @PostMapping
    public ApiResponse<PostResponse> createPost(@RequestBody PostCreationRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(@PathVariable String postId, @RequestBody PostUpdateRequest request) {
        return ApiResponse.<PostResponse>builder().result(postService.updatePost(postId, request)).build();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
        return ApiResponse.<String>builder().result("Post has been deleted").build();
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPost(@PathVariable String postId) {
        return ApiResponse.<PostResponse>builder().result(postService.getPostById(postId)).build();
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getPosts() {
        return ApiResponse.<List<PostResponse>>builder().result(postService.getAllPosts()).build();
    }


}

