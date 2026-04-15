package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.eraser.BookDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/library/v1/books")
@RequiredArgsConstructor
public class BookDeleteController {
    private final BookDelete eraser;

    @DeleteMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String bookId) {
        eraser.dispatch(bookId);
    }
}
