package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseInvoiceNumberException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseInvoice {
    private String number;
    private Double amount;
    private Double taxes;
    private Double total;

    public PurchaseInvoice(String number, Double amount, Double taxes, Double total) {
        check(number);

        this.number = number;
        this.amount = amount;
        this.taxes = taxes;
        this.total = total;
    }

    private void check(String number) {
        if (number == null || number.isBlank()) {
            throw new InvalidPurchaseInvoiceNumberException();
        }
    }
}
