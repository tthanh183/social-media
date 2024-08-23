package com.example.socialmedia.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String name;
    String email;
    String bio;
    LocalDate dob;
    List<String> link;
    String profilePictureUrl;
    boolean isDeleted;
    boolean isPrivate;
    LocalDateTime createdAt;
}
