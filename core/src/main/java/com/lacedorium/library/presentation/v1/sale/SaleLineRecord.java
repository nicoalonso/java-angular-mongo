package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.presentation.v1.book.BookDescriptorRecord;
import lombok.NonNull;

import java.util.List;

public record SaleLineRecord(
        String lineId,
        BookDescriptorRecord book,
        Integer quantity,
        Double price,
        Double discount,
        Double total
) {
    public static @NonNull List<SaleLineRecord> make(@NonNull List<SaleLine> lines) {
        return lines.stream()
                .map(SaleLineRecord::makeItem)
                .toList();
    }

    private static @NonNull SaleLineRecord makeItem(@NonNull SaleLine line) {
        return new SaleLineRecord(
                line.getId(),
                BookDescriptorRecord.make(line.getBook()),
                line.getQuantity(),
                line.getPrice(),
                line.getDiscount(),
                line.getTotal()
        );
    }
}
