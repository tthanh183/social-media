package com.example.socialmedia.service.impl;

import com.example.socialmedia.constant.Constant;
import com.example.socialmedia.dto.request.PostCreationRequest;
import com.example.socialmedia.dto.request.PostUpdateRequest;
import com.example.socialmedia.dto.response.PostResponse;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.AppException;
import com.example.socialmedia.exception.ErrorCode;
import com.example.socialmedia.mapper.PostMapper;
import com.example.socialmedia.repository.IPostRepository;
import com.example.socialmedia.repository.IUserRepository;
import com.example.socialmedia.service.IPostService;
import com.example.socialmedia.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService implements IPostService {
    IPostRepository postRepository;
    IUserRepository userRepository;
    IUserService userService;
    PostMapper postMapper;
    RestTemplate restTemplate;

    @Override
    public PostResponse createPost(PostCreationRequest request) {
        User user = userService.getContextUser();
        String prediction = filterPost(request.getContent());
        if(prediction.equals(Constant.HATE)) {
            throw new AppException(ErrorCode.CONTENT_VIOLATION);
        }
        Post post = postMapper.createPost(request);
        post.setUserId(user.getId());
        post.setCreatedAt(LocalDateTime.now());


        return postMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public PostResponse updatePost(String postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXISTED));

        postMapper.updatePost(post, request);
        post.setUpdatedAt(LocalDateTime.now());

        return postMapper.toPostResponse(postRepository.save(post));
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXISTED));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXISTED));

        return postMapper.toPostResponse(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        User user = userService.getContextUser();
        List<Post> posts = postRepository.findByUserId(user.getId());
        return posts.stream().map(postMapper::toPostResponse).toList();
    }

    private String filterPost(String text) {
        String url = "http://localhost:5000/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        Map<String, String> responseBody = response.getBody();
        return responseBody.get("predicted_class");
    }

}
