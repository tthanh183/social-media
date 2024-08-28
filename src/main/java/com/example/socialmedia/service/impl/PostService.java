package com.example.socialmedia.service.impl;

import com.example.socialmedia.constant.Constant;
import com.example.socialmedia.dto.request.PostCreationRequest;
import com.example.socialmedia.dto.request.PostUpdateRequest;
import com.example.socialmedia.dto.response.PostResponse;
import com.example.socialmedia.entity.Follow;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.User;
import com.example.socialmedia.exception.AppException;
import com.example.socialmedia.exception.ErrorCode;
import com.example.socialmedia.mapper.PostMapper;
import com.example.socialmedia.repository.*;
import com.example.socialmedia.service.IPostService;
import com.example.socialmedia.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService implements IPostService {
    IPostRepository postRepository;
    IUserRepository userRepository;
    IUserService userService;
    PostMapper postMapper;
    RestTemplate restTemplate;
    IFollowRepository followRepository;
    ILikeRepository likeRepository;
    ICommentRepository commentRepository;

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
        post.setLikeCount(likeRepository.countByTargetId(postId));
        post.setCommentCount(commentRepository.countByPostId(postId));
        return postMapper.toPostResponse(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        User user = userService.getContextUser();
        List<Post> posts = postRepository.findByUserId(user.getId());
        posts.forEach(post -> likeRepository.countByTargetId(post.getId()));
        posts.forEach(post -> commentRepository.countByPostId(post.getId()));
        return posts.stream().map(postMapper::toPostResponse).toList();
    }

    @Override
    public List<PostResponse> getLatestPosts(int page, int size) {
        User user = userService.getContextUser();
        List<String> followingIds = followRepository.findByFollowerId(user.getId()).stream()
                .map(Follow::getFolloweeId)
                .toList();

        int followPostSize = (int) (size * 0.6);
        int randomPostSize = size - followPostSize;

        List<Post> posts = new ArrayList<>();

        if (!followingIds.isEmpty()) {
            Pageable followPageable = PageRequest.of(page, followPostSize);
            posts = postRepository.findByUserIdInOrderByCreatedAtDesc(followingIds, followPageable);
        }

        int totalPostCount = (int) postRepository.count();
        int maxPage = totalPostCount / randomPostSize;
        int randomPage = (int) (Math.random() * maxPage);
        Pageable randomPageable = PageRequest.of(randomPage, randomPostSize);

        List<Post> randomPosts = postRepository.findByUserIdNotInOrderByCreatedAtDesc(followingIds, randomPageable);

        posts.addAll(randomPosts);

        Collections.shuffle(posts);

        posts = posts.stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .collect(Collectors.toList());

        if (posts.size() > size) {
            posts = posts.subList(0, size);
        }

        return posts.stream()
                .map(postMapper::toPostResponse)
                .collect(Collectors.toList());
    }





    @Override
    public List<PostResponse> createPosts(List<PostCreationRequest> requests) {
        User user = userService.getContextUser();
        List<Post> posts = requests.stream().map(postMapper::createPost).toList();
        posts.forEach(post -> post.setUserId(user.getId()));
        posts.forEach(post -> post.setCreatedAt(LocalDateTime.now()));

        postRepository.saveAll(posts);
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
