package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.presentation.v1.customer.CustomerDescriptorRecord;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record SaleListRecord(
        String id,
        CustomerDescriptorRecord customer,
        String number,
        SaleInvoiceRecord invoice,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull SaleListRecord make(@NonNull Sale sale) {
        return new SaleListRecord(
                sale.getId(),
                CustomerDescriptorRecord.make(sale.getCustomer()),
                sale.getNumber(),
                SaleInvoiceRecord.make(sale.getInvoice()),
                sale.getCreatedBy(),
                Result.formatDate(sale.getCreatedAt()),
                sale.getUpdatedBy(),
                Result.formatDate(sale.getUpdatedAt())
        );
    }
}
