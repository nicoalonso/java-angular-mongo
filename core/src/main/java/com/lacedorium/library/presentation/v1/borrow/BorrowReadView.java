package com.lacedorium.library.presentation.v1.borrow;

import com.lacedorium.library.application.borrow.reader.BorrowDecorator;
import com.lacedorium.library.presentation.v1.identity.Result;

public class BorrowReadView extends Result<BorrowReadRecord, BorrowDecorator> {
    public BorrowReadView(BorrowDecorator borrow) {
        super(borrow, BorrowReadRecord::make);
    }
}
