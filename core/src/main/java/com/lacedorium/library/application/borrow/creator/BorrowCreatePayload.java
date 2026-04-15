package com.lacedorium.library.application.borrow.creator;

import java.util.List;

public record BorrowCreatePayload (
        String customerId,
        List<BorrowLinePayload> lines
) {
}
