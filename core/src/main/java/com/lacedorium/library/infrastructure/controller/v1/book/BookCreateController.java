package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.creator.BookCreate;
import com.lacedorium.library.application.book.creator.BookCreatePayload;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.presentation.v1.book.BookReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class BookCreateController {
    private final BookCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public BookReadView create(@RequestBody BookCreatePayload payload) {
        Book book = creator.dispatch(payload);
        return new BookReadView(book);
    }
}
