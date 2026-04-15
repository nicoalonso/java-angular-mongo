package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.sanctioner.BorrowPenaltyEvent;
import com.lacedorium.library.domain.bus.DomainBus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/borrows")
@RequiredArgsConstructor
public class BorrowManualPenaltyController {
    private final DomainBus bus;

    @PostMapping("/manual-penalty")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void manualPenalty() {
        bus.dispatch(new BorrowPenaltyEvent());
    }
}
