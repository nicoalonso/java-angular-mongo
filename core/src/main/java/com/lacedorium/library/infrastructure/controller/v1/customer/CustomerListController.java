package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.list.CustomerList;
import com.lacedorium.library.application.customer.list.CustomerListQuery;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.presentation.v1.customer.CustomerReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/customers")
@RequiredArgsConstructor
public class CustomerListController {
    private final CustomerList lister;

    @GetMapping()
    public ListView<CustomerReadRecord> listCustomers(@ModelAttribute CustomerListQuery queryParams) {
        ListQuery query = ListQuery.parse(queryParams);
        ListResult<Customer> result = lister.dispatch(query);
        return ListView.of(result, CustomerReadRecord::make);
    }
}
