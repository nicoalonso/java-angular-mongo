package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.sale.exception.InvalidSaleNumberException;
import com.lacedorium.library.fixtures.mothers.CustomerMother;
import com.lacedorium.library.fixtures.mothers.SaleInvoiceMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Customer customer;
    private SaleInvoice invoice;

    @BeforeEach
    void setUp() {
        customer = CustomerMother.johnDoe().build();
        invoice = SaleInvoiceMother.johnDoeSale1().build();
    }

    @Test
    void shouldFailWhenInvalidNumber() {
        assertThrows(
                InvalidSaleNumberException.class,
                () -> new Sale(customer, "", invoice, "test")
        );
    }

    @Test
    void shouldCreate() {
        Sale sale = new Sale(customer, "123456789", invoice, "test");

        assertEquals(customer.getId(), sale.getCustomer().getId());
        assertEquals("123456789", sale.getNumber());
        assertEquals(invoice, sale.getInvoice());
    }
}