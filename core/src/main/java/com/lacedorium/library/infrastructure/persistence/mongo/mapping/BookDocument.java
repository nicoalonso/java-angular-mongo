package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.AuthorDescriptorEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.BookDetailEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.BookSaleEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.EditorialDescriptorEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "books")
public class BookDocument {
    @Id
    private String id;
    private String title;
    private String description;
    private AuthorDescriptorEmbedded author;
    private EditorialDescriptorEmbedded editorial;
    private BookDetailEmbedded detail;
    private BookSaleEmbedded sale;
    private Integer stock;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
