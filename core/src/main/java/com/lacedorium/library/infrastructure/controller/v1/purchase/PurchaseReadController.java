package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.reader.PurchaseDecorator;
import com.lacedorium.library.application.purchase.reader.PurchaseRead;
import com.lacedorium.library.presentation.v1.purchase.PurchaseReadView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/v1/purchases")
@RequiredArgsConstructor
public class PurchaseReadController {
    private final PurchaseRead reader;

    @GetMapping("/{purchaseId}")
    public PurchaseReadView read(@PathVariable String purchaseId) {
        PurchaseDecorator purchase = reader.dispatch(purchaseId);
        return new PurchaseReadView(purchase);
    }
}
