package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.creator.CustomerCreate;
import com.lacedorium.library.application.customer.creator.CustomerCreatePayload;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.presentation.v1.customer.CustomerReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/v1/customers")
@RequiredArgsConstructor
public class CustomerCreateController {
    private final CustomerCreate creator;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerReadView create(@RequestBody CustomerCreatePayload payload) {
        Customer customer = creator.dispatch(payload);
        return new CustomerReadView(customer);
    }
}
