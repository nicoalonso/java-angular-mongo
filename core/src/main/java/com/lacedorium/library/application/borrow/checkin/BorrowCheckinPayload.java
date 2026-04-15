package com.lacedorium.library.application.borrow.checkin;

import java.util.List;

public record BorrowCheckinPayload(
        List<BorrowLineCheckinPayload> lines
) {
}
