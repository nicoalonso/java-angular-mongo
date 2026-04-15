package com.lacedorium.library.presentation.v1.borrow;

import com.lacedorium.library.application.borrow.reader.BorrowDecorator;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.presentation.v1.customer.CustomerDescriptorRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record BorrowReadRecord(
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
        List<BorrowLineRecord> lines,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull BorrowReadRecord make(@NonNull BorrowDecorator decorator) {
        Borrow borrow = decorator.borrow();

        return new BorrowReadRecord(
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
                BorrowLineRecord.make(decorator.lines()),
                borrow.getCreatedBy(),
                Result.formatDate(borrow.getCreatedAt()),
                borrow.getUpdatedBy(),
                Result.formatDate(borrow.getUpdatedAt())
        );
    }
}
