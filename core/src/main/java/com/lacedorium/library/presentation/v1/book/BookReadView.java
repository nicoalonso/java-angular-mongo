package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.presentation.v1.identity.Result;

public class BookReadView extends Result<BookReadRecord, Book> {
    public BookReadView(Book book) {
        super(book, BookReadRecord::make);
    }
}
