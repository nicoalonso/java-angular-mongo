package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.application.sale.reader.SaleDecorator;
import com.lacedorium.library.presentation.v1.identity.Result;

public class SaleReadView extends Result<SaleReadRecord, SaleDecorator> {
    public SaleReadView(SaleDecorator sale) {
        super(sale, SaleReadRecord::make);
    }
}
