package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.presentation.v1.identity.Result;

public class PurchaseCreateView extends Result<PurchaseListRecord, Purchase> {
    public PurchaseCreateView(Purchase purchase) {
        super(purchase, PurchaseListRecord::make);
    }
}
