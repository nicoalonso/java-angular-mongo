package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.presentation.v1.identity.Result;
import com.lacedorium.library.presentation.v1.provider.ProviderDescriptorRecord;
import lombok.NonNull;

public record PurchaseListRecord (
        String id,
        ProviderDescriptorRecord provider,
        String purchasedAt,
        PurchaseInvoiceRecord invoice,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull PurchaseListRecord make(@NonNull Purchase purchase) {
        return new PurchaseListRecord(
                purchase.getId(),
                ProviderDescriptorRecord.make(purchase.getProvider()),
                Result.formatShortDate(purchase.getPurchasedAt()),
                PurchaseInvoiceRecord.make(purchase.getInvoice()),
                purchase.getCreatedBy(),
                Result.formatDate(purchase.getCreatedAt()),
                purchase.getUpdatedBy(),
                Result.formatDate(purchase.getUpdatedAt())
        );
    }
}
