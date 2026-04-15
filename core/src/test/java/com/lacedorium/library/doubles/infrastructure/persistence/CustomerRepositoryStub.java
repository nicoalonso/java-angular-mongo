package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.CustomerMother;
import lombok.SneakyThrows;

import java.util.Optional;

public class CustomerRepositoryStub
        extends EntityRepositoryStub<Customer>
        implements CustomerRepository {
    @SneakyThrows
    @Override
    public Optional<Customer> obtainByName(String name, String surname) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public Optional<Customer> obtainByNumber(String number) {
        throwException();
        return Optional.ofNullable(read);
    }

    @Override
    protected void makeFixtures() {
        Customer johnDoe = CustomerMother.johnDoe().build();
        addFixture(Ref.CustomerJohnDoe, johnDoe);
    }
}
