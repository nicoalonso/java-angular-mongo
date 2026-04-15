package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.reader.BorrowDecorator;
import com.lacedorium.library.application.borrow.reader.BorrowRead;
import com.lacedorium.library.presentation.v1.borrow.BorrowReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/borrows")
@RequiredArgsConstructor
public class BorrowReadController {
    private final BorrowRead reader;

    @GetMapping("/{borrowId}")
    public BorrowReadView read(@PathVariable String borrowId) {
        BorrowDecorator borrow = reader.dispatch(borrowId);
        return new BorrowReadView(borrow);
    }
}
