package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.CustomerDescriptorEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document( collection = "borrows" )
public class BorrowDocument {
    @Id
    private String id;
    private CustomerDescriptorEmbedded customer;
    private String number;
    private LocalDateTime borrowDate;
    private Integer totalBooks;
    private LocalDateTime dueDate;
    private Integer totalReturnedBooks;
    private Boolean returned;
    private LocalDateTime returnedDate;
    private Boolean penalty;
    private Double penaltyAmount;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
