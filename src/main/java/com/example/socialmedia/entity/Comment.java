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
@Document(collection = "comments")
public class Comment {
    @Id
    String id;
    String userId;
    String postId;
    String content;
    List<String> images;
    List<String> videos;
    LocalDateTime createdAt;
}

