package com.lacedorium.library.application.book.reader;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRead {
    private final BookRepository repoBook;

    public Book dispatch(String bookId) {
        return repoBook.obtainById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
