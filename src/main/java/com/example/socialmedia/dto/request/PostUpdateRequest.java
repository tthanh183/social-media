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
    List<String> images;
    List<String> videos;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String status;
}
