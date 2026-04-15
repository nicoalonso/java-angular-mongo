package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.reader.BookRead;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.presentation.v1.book.BookReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class BookReadController {
    private final BookRead reader;

    @GetMapping("/{bookId}")
    public BookReadView getBook(@PathVariable String bookId) {
        Book book = reader.dispatch(bookId);
        return new BookReadView(book);
    }
}
