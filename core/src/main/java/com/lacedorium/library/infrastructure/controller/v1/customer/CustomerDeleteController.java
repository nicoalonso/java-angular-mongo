package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.eraser.CustomerDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/customers")
@RequiredArgsConstructor
public class CustomerDeleteController {
    private final CustomerDelete eraser;

    @RequestMapping("/{customerId}")
    public void deleteCustomer(@PathVariable String customerId) {
        eraser.dispatch(customerId);
    }
}
