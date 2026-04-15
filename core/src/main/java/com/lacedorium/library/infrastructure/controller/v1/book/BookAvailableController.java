package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.available.BookAvailable;
import com.lacedorium.library.presentation.v1.book.BookAvailableView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/books")
@RequiredArgsConstructor
public class BookAvailableController {
    private final BookAvailable inspector;

    @GetMapping("/{bookId}/available")
    public BookAvailableView available(
            @PathVariable String bookId,
            @RequestParam(value = "sale", defaultValue = "false") Boolean isSale
    ) {
        Boolean result = inspector.dispatch(bookId, isSale);
        return new BookAvailableView(result);
    }
}
