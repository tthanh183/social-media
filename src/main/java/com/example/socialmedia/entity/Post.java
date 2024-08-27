package com.example.socialmedia.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "posts")
public class Post {
    @Id
    String id;
    String userId;
    String content;
    String location;
    List<String> photos;
    List<String> videos;
    List<String> hashtags;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String status;
    Long likeCount;
    Long commentCount;
}
