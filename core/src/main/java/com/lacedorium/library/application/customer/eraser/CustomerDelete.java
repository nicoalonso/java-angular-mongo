package com.lacedorium.library.application.customer.eraser;

import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.domain.sale.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDelete {
    private final CustomerRepository repoCustomer;
    private final SaleRepository repoSale;
    private final BorrowRepository repoBorrow;

    public void dispatch(String customerId) {
        Customer customer = repoCustomer.obtainById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        checkAssociated(customer);

        repoCustomer.remove(customerId);
    }

    private void checkAssociated(@NonNull Customer customer) {
        List<?> borrows = repoBorrow.obtainByCustomer(customer.getId(), 1);
        if (!borrows.isEmpty()) {
            throw new CustomerAssociatedException("The customer is associated with one or more borrows.");
        }

        List<?> sales = repoSale.obtainByCustomer(customer.getId(), 1);
        if (!sales.isEmpty()) {
            throw new CustomerAssociatedException("The customer is associated with one or more sales.");
        }
    }
}
