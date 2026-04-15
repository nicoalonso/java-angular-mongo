package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class CustomerMother extends BaseMother<Customer> {
    private static final Map<String, Object> JOHN_DOE = Map.of(
            "name", "John",
            "surname", "Doe",
            "membership", lazy(MembershipMother::active),
            "contact", lazy(ContactInfoMother::doe),
            "address", lazy(AddressMother::anytown),
            "vatNumber", "12345667A",
            "createdBy", "test"
    );

    protected CustomerMother(Map<String, Object> base) {
        super(Customer.class, base);
    }

    public static @NonNull CustomerMother johnDoe() {
        return new CustomerMother(JOHN_DOE);
    }
}
