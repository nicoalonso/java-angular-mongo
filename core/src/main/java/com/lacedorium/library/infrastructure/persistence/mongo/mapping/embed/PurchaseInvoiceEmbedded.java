package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

@Data
public class PurchaseInvoiceEmbedded {
    private String number;
    private Double amount;
    private Double taxes;
    private Double total;
}
