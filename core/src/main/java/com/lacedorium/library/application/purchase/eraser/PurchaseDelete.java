package com.lacedorium.library.application.purchase.eraser;

import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainBus;
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
public class PurchaseDelete {
    private final PurchaseRepository repoPurchase;
    private final PurchaseLineRepository repoPurchaseLine;
    private final DomainBus bus;

    public void dispatch(String purchaseId) {
        Purchase purchase = repoPurchase.obtainById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        List<PurchaseLine> lines = repoPurchaseLine.obtainByPurchase(purchaseId);
        for (PurchaseLine line : lines) {
            repoPurchaseLine.remove(line.getId());
        }
        repoPurchase.remove(purchase.getId());

        List<BookDescriptor> books = lines.stream()
                .map(PurchaseLine::getBook)
                .toList();
        PurchaseDeletedEvent event = new PurchaseDeletedEvent(purchase, books);
        bus.dispatch(event);
    }
}
