package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import lombok.NonNull;

import java.util.List;

public class BookSaleInspect extends BookInspector {
    private static final int MIN_STOCK_FOR_SALE = 3;

    public BookSaleInspect(BorrowLineRepository repoBorrowLine) {
        super(repoBorrowLine);
    }

    @Override
    public Boolean available(@NonNull Book book) {
        List<BorrowLine> activeBorrowLines = obtainActiveBorrowLines(book);
        int availableStock = book.getStock() - activeBorrowLines.size();
        return availableStock >= MIN_STOCK_FOR_SALE;
    }
}
