package com.lacedorium.library.application.customer.updater;

import com.lacedorium.library.domain.common.Address;
import com.lacedorium.library.domain.customer.ContactInfo;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUpdate {
    private final CustomerRepository repoCustomer;
    private final UserRepository repoUser;

    public @NonNull Customer dispatch(String customerId, @NonNull CustomerUpdatePayload payload) {
        Customer customer = repoCustomer.obtainById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        User user = repoUser.obtainUser();
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

        customer.modify(
                payload.name(),
                payload.surname(),
                contact,
                address,
                payload.vatNumber(),
                payload.membership().active(),
                user.getName()
        );

        repoCustomer.save(customer);
        return customer;
    }
}
