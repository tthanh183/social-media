package com.example.socialmedia.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String name;
    String email;
    String password;
    String bio;
    LocalDate dob;
    List<String> link;
    String profilePictureUrl;
}

