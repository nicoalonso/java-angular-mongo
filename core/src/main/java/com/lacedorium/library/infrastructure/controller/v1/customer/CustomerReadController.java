package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.reader.CustomerRead;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.presentation.v1.customer.CustomerReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/customers")
@RequiredArgsConstructor
public class CustomerReadController {
    private final CustomerRead reader;

    @GetMapping("/{customerId}")
    public CustomerReadView read(@PathVariable String customerId) {
        Customer customer = reader.dispatch(customerId);
        return new CustomerReadView(customer);
    }
}
