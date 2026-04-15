package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.checkin.BorrowCheckin;
import com.lacedorium.library.application.borrow.checkin.BorrowCheckinPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/borrows")
@RequiredArgsConstructor
public class BorrowCheckinController {
    private final BorrowCheckin checker;

    @PatchMapping("/{borrowId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void checkin(@PathVariable String borrowId, @RequestBody BorrowCheckinPayload payload) {
        checker.dispatch(borrowId, payload);
    }
}
