package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "authors")
public class AuthorDocument {
    @Id
    private String id;
    private String name;
    private String realName;
    private String genres;
    private String biography;
    private String nationality;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private String photoUrl;
    private String website;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
