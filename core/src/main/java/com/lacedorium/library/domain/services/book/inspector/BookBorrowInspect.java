package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import lombok.NonNull;

import java.util.List;

public class BookBorrowInspect extends BookInspector {
    public BookBorrowInspect(BorrowLineRepository repoBorrowLine) {
        super(repoBorrowLine);
    }

    @Override
    public Boolean available(@NonNull Book book) {
        List<BorrowLine> activeBorrowLines = obtainActiveBorrowLines(book);
        return book.getStock() > activeBorrowLines.size();
    }
}
