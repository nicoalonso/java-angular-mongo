package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.application.sale.reader.SaleDecorator;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.presentation.v1.customer.CustomerDescriptorRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

import java.util.List;

public record SaleReadRecord(
        String id,
        CustomerDescriptorRecord customer,
        String number,
        SaleInvoiceRecord invoice,
        List<SaleLineRecord> lines,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull SaleReadRecord make(@NonNull SaleDecorator decorator) {
        Sale sale = decorator.sale();

        return new SaleReadRecord(
                sale.getId(),
                CustomerDescriptorRecord.make(sale.getCustomer()),
                sale.getNumber(),
                SaleInvoiceRecord.make(sale.getInvoice()),
                SaleLineRecord.make(decorator.lines()),
                sale.getCreatedBy(),
                Result.formatDate(sale.getCreatedAt()),
                sale.getUpdatedBy(),
                Result.formatDate(sale.getUpdatedAt())
        );
    }
}
