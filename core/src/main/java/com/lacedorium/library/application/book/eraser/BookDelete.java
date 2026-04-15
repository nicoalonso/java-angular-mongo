package com.lacedorium.library.application.book.eraser;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.domain.sale.SaleLineRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDelete {
    private final BookRepository repoBook;
    private final PurchaseLineRepository repoPurchaseLine;
    private final SaleLineRepository repoSaleLine;
    private final BorrowLineRepository repoBorrowLine;

    public void dispatch(String bookId) {
        Book book = repoBook.obtainById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        checkAssociated(book);

        repoBook.remove(book.getId());
    }

    private void checkAssociated(@NonNull Book book) {
        if (!repoPurchaseLine.obtainByBook(book.getId(), 1).isEmpty()) {
            throw new BookAssociatedException("The book is associated with one or more purchases or sales.");
        }

        if (!repoSaleLine.obtainByBook(book.getId(), 1).isEmpty()) {
            throw new BookAssociatedException("Cannot delete book with associated borrow lines.");
        }

        if (!repoBorrowLine.obtainByBook(book.getId(), 1).isEmpty()) {
            throw new BookAssociatedException("Cannot delete book with associated sale lines.");
        }
    }
}
