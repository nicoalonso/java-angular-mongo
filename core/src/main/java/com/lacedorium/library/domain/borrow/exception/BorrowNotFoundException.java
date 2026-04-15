package com.lacedorium.library.domain.borrow.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class BorrowNotFoundException extends NotFoundException {
    public BorrowNotFoundException(String borrowId) {
        super("Borrow with id '" + borrowId + "' not found");
    }
}
