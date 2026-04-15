package com.lacedorium.library.presentation.v1.customer;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.presentation.v1.identity.Result;

public class CustomerReadView extends Result<CustomerReadRecord, Customer> {
    public CustomerReadView(Customer customer) {
        super(customer, CustomerReadRecord::make);
    }
}
