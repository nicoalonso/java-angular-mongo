package com.lacedorium.library.application.borrow.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class BorrowLinesEmptyException extends BadRequestException {
    public BorrowLinesEmptyException() {
        super("The borrow invoice must have at least one line");
    }
}
