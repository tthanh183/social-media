package com.example.socialmedia.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "users")
public class User {
    @Id
    ObjectId id;
    String name;
    String email;
    String password;
    String bio;
    LocalDate dob;
    List<String> link;
    String profilePictureUrl;
    boolean isDeleted;
    boolean isPrivate;
    LocalDateTime createdAt;
}
