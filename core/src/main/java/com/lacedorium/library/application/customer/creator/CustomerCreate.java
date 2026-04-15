package com.lacedorium.library.application.customer.creator;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.customer.ContactInfo;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.Membership;
import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreate {
    private final CustomerRepository repoCustomer;
    private final SequenceNumberRepository repoSequenceNumber;
    private final UserRepository repoUser;

    public Customer dispatch(@NonNull CustomerCreatePayload payload) {
        checkAlreadyExists(payload);

        String number = generateSequenceNumber();
        Membership membership = new Membership(number);

        ContactInfo contact = new ContactInfo(
                payload.contact().email(),
                payload.contact().phone1(),
                payload.contact().phone2()
        );
        Address address = new Address(
                payload.address().street(),
                payload.address().postalCode(),
                payload.address().city(),
                payload.address().province(),
                payload.address().country()
        );
        User user = repoUser.obtainUser();

        Customer customer = new Customer(
                payload.name(),
                payload.surname(),
                membership,
                contact,
                address,
                payload.vatNumber(),
                user.getName()
        );

        repoCustomer.save(customer);
        return customer;
    }

    private void checkAlreadyExists(@NonNull CustomerCreatePayload payload) {
        if (repoCustomer.obtainByName(payload.name(), payload.surname()).isPresent()) {
            throw new CustomerAlreadyExistsException(payload.name(), payload.surname());
        }
    }

    private String generateSequenceNumber() {
        String number;

        do {
            SequenceNumber sequenceNumber = repoSequenceNumber.nextNumber(SequenceType.MEMBERSHIP);
            number = sequenceNumber.format();

        } while (repoCustomer.obtainByNumber(number).isPresent());

        return number;
    }
}
