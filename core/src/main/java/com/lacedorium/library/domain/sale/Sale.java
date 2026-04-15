package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerDescriptor;
import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.sale.exception.InvalidSaleNumberException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class Sale extends Entity {
    private CustomerDescriptor customer;
    private String number;
    private SaleInvoice invoice;

    public Sale(
            @NonNull Customer customer,
            String number,
            SaleInvoice invoice,
            String createdBy
    ) {
        super(createdBy);

        check(number);

        this.customer = customer.getDescriptor();
        this.number = number;
        this.invoice = invoice;
    }

    private void check(String number) {
        if (number == null || number.isBlank()) {
            throw new InvalidSaleNumberException();
        }
    }
}
