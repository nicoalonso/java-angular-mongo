package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

@Data
public class BookSaleEmbedded {
    private Boolean saleable;
    private Double price;
    private Double discount;
}
