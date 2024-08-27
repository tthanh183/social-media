package com.example.socialmedia.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostUpdateRequest {
    String content;
    String location;
    List<String> photos;
    List<String> videos;
    List<String> hashtags;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String status;
}
