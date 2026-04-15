package com.lacedorium.library.domain.customer;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.identity.exception.NameEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class Customer extends Entity {
    private String name;
    private String surname;
    private Membership membership;
    private ContactInfo contact;
    private Address address;
    private String vatNumber;

    public Customer(
            String name,
            String surname,
            Membership membership,
            ContactInfo contact,
            Address address,
            String vatNumber,
            String createdBy
    ) {
        super(createdBy);

        check(name);

        this.name = name;
        this.surname = surname;
        this.membership = membership;
        this.contact = contact;
        this.address = address;
        this.vatNumber = vatNumber;
    }

    public void modify(
            String name,
            String surname,
            ContactInfo contact,
            Address address,
            String vatNumber,
            Boolean active,
            String updatedBy
    ) {
        check(name);

        this.name = name;
        this.surname = surname;
        this.membership = membership.withActive(active);
        this.contact = contact;
        this.address = address;
        this.vatNumber = vatNumber;
        updated(updatedBy);
    }

    private void check(String name) {
        if (name == null || name.isBlank()) {
            throw new NameEmptyException();
        }
    }

    public @NonNull CustomerDescriptor getDescriptor() {
        return new CustomerDescriptor(id, name, surname, vatNumber, membership.getNumber());
    }
}
