package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.presentation.v1.book.BookDescriptorRecord;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record PurchaseLineRecord (
        String lineId,
        BookDescriptorRecord book,
        Integer quantity,
        Double unitPrice,
        Double discountPercentage,
        Double total
) {
    public static @NonNull List<PurchaseLineRecord> make(@NonNull List<PurchaseLine> lines) {
        return lines.stream()
                .map(PurchaseLineRecord::makeItem)
                .toList();
    }

    private static @NonNull PurchaseLineRecord makeItem(@NonNull PurchaseLine line) {
        return new PurchaseLineRecord(
                line.getId(),
                BookDescriptorRecord.make(line.getBook()),
                line.getQuantity(),
                line.getUnitPrice(),
                line.getDiscountPercentage(),
                line.getTotal()
        );
    }
}
