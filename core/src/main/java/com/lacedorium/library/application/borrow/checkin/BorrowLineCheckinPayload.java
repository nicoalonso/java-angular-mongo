package com.lacedorium.library.application.borrow.checkin;

public record BorrowLineCheckinPayload(
        String lineId,
        String bookId,
        Boolean returned
) {
}
