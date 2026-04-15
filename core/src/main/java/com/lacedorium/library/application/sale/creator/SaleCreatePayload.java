package com.lacedorium.library.application.sale.creator;

import com.lacedorium.library.application.sale.creator.payload.SaleInvoicePayload;
import com.lacedorium.library.application.sale.creator.payload.SaleLinePayload;

import java.util.List;

public record SaleCreatePayload (
        String customerId,
        SaleInvoicePayload invoice,
        List<SaleLinePayload> lines
) { }
