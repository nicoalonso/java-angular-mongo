package com.lacedorium.library.application.customer.reader;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRead {
    private final CustomerRepository repoCustomer;

    public Customer dispatch(String customerId) {
        return repoCustomer.obtainById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
