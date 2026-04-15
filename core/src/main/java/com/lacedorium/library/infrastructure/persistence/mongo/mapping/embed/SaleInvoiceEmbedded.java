package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleInvoiceEmbedded {
    private LocalDateTime date;
    private Double amount;
    private Double taxPercentage;
    private Double taxes;
    private Double total;
}
