package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDetailEmbedded {
    private String edition;
    private String isbn;
    private String language;
    private LocalDateTime publishedAt;
    private Integer pages;
}
