package com.lacedorium.library.presentation.v1.borrow;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.presentation.v1.identity.Result;

public class BorrowCreateView extends Result<BorrowListRecord, Borrow> {
    public BorrowCreateView(Borrow borrow) {
        super(borrow, BorrowListRecord::make);
    }
}
