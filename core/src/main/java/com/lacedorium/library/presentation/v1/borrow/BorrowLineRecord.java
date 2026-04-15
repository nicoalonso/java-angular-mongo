package com.lacedorium.library.presentation.v1.borrow;

import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.presentation.v1.book.BookDescriptorRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record BorrowLineRecord(
        String lineId,
        BookDescriptorRecord book,
        Boolean returned,
        String returnedDate,
        Boolean penalty,
        Double penaltyAmount
) {
    public static @NonNull List<BorrowLineRecord> make(@NonNull List<BorrowLine> lines) {
        return lines.stream()
                .map(BorrowLineRecord::makeItem)
                .toList();
    }

    private static @NonNull BorrowLineRecord makeItem(@NonNull BorrowLine line) {
        return new BorrowLineRecord(
                line.getId(),
                BookDescriptorRecord.make(line.getBook()),
                line.getReturned(),
                Result.formatShortDate(line.getReturnedDate()),
                line.getPenalty(),
                line.getPenaltyAmount()
        );
    }
}
