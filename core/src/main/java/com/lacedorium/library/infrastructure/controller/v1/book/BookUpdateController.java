package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.updater.BookUpdate;
import com.lacedorium.library.application.book.updater.BookUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class BookUpdateController {
    private final BookUpdate updater;

    @PutMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String bookId, @RequestBody BookUpdatePayload payload) {
        updater.dispatch(bookId, payload);
    }
}
