package com.lacedorium.library.domain.customer.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String customerId) {
        super("Customer with id '" + customerId + "' not found");
    }
}
