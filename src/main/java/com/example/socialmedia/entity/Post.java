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
@Document(collection = "posts")
public class Post {
    @Id
    ObjectId id;
    ObjectId userId;
    String caption;
    String content;
    String location;
    List<ObjectId> images;
    List<ObjectId> videos;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String status;
}
