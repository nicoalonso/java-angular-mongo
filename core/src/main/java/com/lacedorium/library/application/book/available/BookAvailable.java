package com.lacedorium.library.application.book.available;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.services.book.inspector.BookInspectFactory;
import com.lacedorium.library.domain.services.book.inspector.BookInspector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookAvailable {
    private final BookRepository repoBook;
    private final BookInspectFactory factory;

    public Boolean dispatch(String bookId, Boolean isSale) {
        Book book = repoBook.obtainById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        BookInspector inspector = factory.create(isSale);
        return inspector.available(book);
    }
}
