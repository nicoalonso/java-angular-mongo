package com.lacedorium.library.domain.borrow.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidBorrowNumberException extends BadRequestException {
    public InvalidBorrowNumberException() {
        super("The borrow number is required.");
    }
}
