package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.application.purchase.reader.PurchaseDecorator;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.presentation.v1.identity.Result;
import com.lacedorium.library.presentation.v1.provider.ProviderDescriptorRecord;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record PurchaseReadRecord (
        String id,
        ProviderDescriptorRecord provider,
        String purchasedAt,
        PurchaseInvoiceRecord invoice,
        List<PurchaseLineRecord> lines,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull PurchaseReadRecord make(@NonNull PurchaseDecorator decorator) {
        Purchase purchase = decorator.purchase();

        return new PurchaseReadRecord(
                purchase.getId(),
                ProviderDescriptorRecord.make(purchase.getProvider()),
                Result.formatShortDate(purchase.getPurchasedAt()),
                PurchaseInvoiceRecord.make(purchase.getInvoice()),
                PurchaseLineRecord.make(decorator.lines()),
                purchase.getCreatedBy(),
                Result.formatDate(purchase.getCreatedAt()),
                purchase.getUpdatedBy(),
                Result.formatDate(purchase.getUpdatedAt())
        );
    }
}
