package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.sale.exception.InvalidSaleDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SaleInvoice {
    private LocalDateTime date;
    private Double amount;
    private Double taxPercentage;
    private Double taxes;
    private Double total;

    public SaleInvoice(
            LocalDateTime date,
            Double amount,
            Double taxPercentage,
            Double taxes,
            Double total
    ) {
        check(date);

        this.date = date;
        this.amount = amount;
        this.taxPercentage = taxPercentage;
        this.taxes = taxes;
        this.total = total;
    }

    private void check(LocalDateTime date) {
        LocalDateTime limit = LocalDateTime.now().plusDays(1);
        if (date == null || date.isAfter(limit)) {
            throw new InvalidSaleDateException();
        }
    }
}
