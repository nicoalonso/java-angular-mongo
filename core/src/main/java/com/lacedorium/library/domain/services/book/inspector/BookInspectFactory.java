package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInspectFactory {
    private final BorrowLineRepository repoBorrowLine;

    public @NonNull BookInspector create(@NonNull Boolean isSale) {
        if (isSale) {
            return new BookSaleInspect(repoBorrowLine);
        } else {
            return new BookBorrowInspect(repoBorrowLine);
        }
    }
}
