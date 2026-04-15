package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.presentation.v1.identity.Result;

public class SaleCreateView extends Result<SaleListRecord, Sale> {
    public SaleCreateView(Sale sale) {
        super(sale, SaleListRecord::make);
    }
}
