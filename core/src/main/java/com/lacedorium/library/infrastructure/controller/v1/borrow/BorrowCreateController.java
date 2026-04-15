package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.creator.BorrowCreate;
import com.lacedorium.library.application.borrow.creator.BorrowCreatePayload;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.presentation.v1.borrow.BorrowCreateView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/borrows")
@RequiredArgsConstructor
public class BorrowCreateController {
    private final BorrowCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public BorrowCreateView create(@RequestBody BorrowCreatePayload payload) {
        Borrow borrow = creator.dispatch(payload);
        return new BorrowCreateView(borrow);
    }
}
