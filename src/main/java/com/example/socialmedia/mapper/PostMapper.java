package com.example.socialmedia.mapper;

import com.example.socialmedia.dto.request.PostCreationRequest;
import com.example.socialmedia.dto.request.PostUpdateRequest;
import com.example.socialmedia.dto.response.PostResponse;
import com.example.socialmedia.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {
    Post createPost(PostCreationRequest request);
    PostResponse toPostResponse(Post post);
    void updatePost(@MappingTarget Post post, PostUpdateRequest request);
}
