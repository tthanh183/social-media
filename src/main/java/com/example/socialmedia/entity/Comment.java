package com.example.socialmedia.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
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
    ObjectId id;
    ObjectId userId;
    ObjectId postId;
    String content;
    List<ObjectId> images;
    List<ObjectId> videos;
    LocalDateTime createdAt;
}

