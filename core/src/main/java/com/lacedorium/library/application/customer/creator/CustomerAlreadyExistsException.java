package com.lacedorium.library.application.customer.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class CustomerAlreadyExistsException extends BadRequestException {
    public CustomerAlreadyExistsException(String name, String surname) {
        super("Customer with name '" + name + "' and surname '" + surname + "' already exists");
    }
}
