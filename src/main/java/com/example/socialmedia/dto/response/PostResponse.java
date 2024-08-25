package com.example.socialmedia.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String id;
    String userId;
    String content;
    String location;
    List<String> images;
    List<String> videos;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String status;
}
