package com.example.socialmedia.service;

import com.example.socialmedia.dto.request.PostCreationRequest;
import com.example.socialmedia.dto.request.PostUpdateRequest;
import com.example.socialmedia.dto.response.PostResponse;

import java.util.List;

public interface IPostService {
    public PostResponse createPost(PostCreationRequest request);
    public PostResponse updatePost(String postId, PostUpdateRequest request);
    public void deletePost(String postId);
    public PostResponse getPostById(String postId);
    public List<PostResponse> getAllPosts();
}
