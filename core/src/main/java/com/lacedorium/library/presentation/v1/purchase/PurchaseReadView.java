package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.application.purchase.reader.PurchaseDecorator;
import com.lacedorium.library.presentation.v1.identity.Result;

public class PurchaseReadView extends Result<PurchaseReadRecord, PurchaseDecorator> {
    public PurchaseReadView(PurchaseDecorator purchase) {
        super(purchase, PurchaseReadRecord::make);
    }
}
