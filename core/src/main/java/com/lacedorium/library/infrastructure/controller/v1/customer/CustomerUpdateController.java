package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.updater.CustomerUpdate;
import com.lacedorium.library.application.customer.updater.CustomerUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/customers")
@RequiredArgsConstructor
public class CustomerUpdateController {
    private final CustomerUpdate updater;

    @PutMapping("/{customerId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable String customerId, @RequestBody CustomerUpdatePayload payload) {
        updater.dispatch(customerId, payload);
    }
}
