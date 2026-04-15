package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import lombok.NonNull;

import java.util.List;

public abstract class BookInspector {
    protected final BorrowLineRepository repoBorrowLine;

    public BookInspector(BorrowLineRepository repoBorrowLine) {
        this.repoBorrowLine = repoBorrowLine;
    }

    public abstract Boolean available(@NonNull Book book);

    protected List<BorrowLine> obtainActiveBorrowLines(@NonNull Book book) {
        return repoBorrowLine.obtainActiveByBook(book.getId());
    }
}
