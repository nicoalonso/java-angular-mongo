package com.lacedorium.library.application.purchase.reader;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import com.lacedorium.library.domain.purchase.exception.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseRead {
    private final PurchaseRepository repoPurchase;
    private final PurchaseLineRepository repoPurchaseLine;

    public PurchaseDecorator dispatch(String purchaseId) {
        Purchase purchase = repoPurchase.obtainById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        List<PurchaseLine> lines = repoPurchaseLine.obtainByPurchase(purchaseId);

        return new PurchaseDecorator(purchase, lines);
    }
}
