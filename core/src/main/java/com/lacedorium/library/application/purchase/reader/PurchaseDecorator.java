package com.lacedorium.library.application.purchase.reader;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseLine;

import java.util.List;

public record PurchaseDecorator(Purchase purchase, List<PurchaseLine> lines) {
}
