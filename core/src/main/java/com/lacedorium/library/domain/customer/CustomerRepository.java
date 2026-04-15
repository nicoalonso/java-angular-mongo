package com.lacedorium.library.domain.customer;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.Optional;

public interface CustomerRepository extends IdentityRepository<Customer> {
     Optional<Customer> obtainByName(String name, String surname);
     Optional<Customer> obtainByNumber(String number);
}
