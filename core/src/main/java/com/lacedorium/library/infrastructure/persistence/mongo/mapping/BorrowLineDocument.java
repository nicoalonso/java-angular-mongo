package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.BookDescriptorEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document( collection = "borrowLines" )
public class BorrowLineDocument {
    @Id
    private String id;
    private String borrow;
    private BookDescriptorEmbedded book;
    private Boolean returned;
    private LocalDateTime returnedDate;
    private Boolean penalty;
    private Double penaltyAmount;
}
