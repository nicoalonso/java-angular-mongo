package com.lacedorium.library.presentation.v1.borrow;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.presentation.v1.customer.CustomerDescriptorRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

public record BorrowListRecord(
        String id,
        CustomerDescriptorRecord customer,
        String number,
        String borrowDate,
        Integer totalBooks,
        String dueDate,
        Integer totalReturnedBooks,
        Boolean returned,
        String returnedDate,
        Boolean penalty,
        Double penaltyAmount,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull BorrowListRecord make(@NonNull Borrow borrow) {
        return new BorrowListRecord(
                borrow.getId(),
                CustomerDescriptorRecord.make(borrow.getCustomer()),
                borrow.getNumber(),
                Result.formatShortDate(borrow.getBorrowDate()),
                borrow.getTotalBooks(),
                Result.formatShortDate(borrow.getDueDate()),
                borrow.getTotalReturnedBooks(),
                borrow.getReturned(),
                Result.formatShortDate(borrow.getReturnedDate()),
                borrow.getPenalty(),
                borrow.getPenaltyAmount(),
                borrow.getCreatedBy(),
                Result.formatDate(borrow.getCreatedAt()),
                borrow.getUpdatedBy(),
                Result.formatDate(borrow.getUpdatedAt())
        );
    }
}
