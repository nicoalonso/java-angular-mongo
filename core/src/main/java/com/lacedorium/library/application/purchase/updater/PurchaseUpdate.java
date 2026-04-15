package com.lacedorium.library.application.purchase.updater;

import com.lacedorium.library.application.purchase.creator.PurchaseMakeable;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.purchase.*;
import com.lacedorium.library.domain.purchase.exception.PurchaseNotFoundException;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseUpdate extends PurchaseMakeable {
    private final PurchaseRepository repoPurchase;
    private final UserRepository repoUser;
    private final DomainBus bus;

    public PurchaseUpdate(
            PurchaseRepository repoPurchase,
            PurchaseLineRepository repoPurchaseLine,
            BookRepository repoBook,
            ProviderRepository repoProvider,
            UserRepository repoUser,
            DomainBus bus
    ) {
        super(repoBook, repoProvider, repoPurchaseLine);

        this.repoPurchase = repoPurchase;
        this.repoUser = repoUser;
        this.bus = bus;
    }

    public Purchase dispatch(String purchaseId, @NonNull PurchaseUpdatePayload payload) {
        Purchase purchase = repoPurchase.obtainById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        check(payload.lines());

        Provider provider = findProvider(payload.providerId());
        PurchaseInvoice invoice = makeInvoice(payload.invoice());
        User user = repoUser.obtainUser();

        purchase.modify(provider, payload.getPurchasedAt(), invoice, user.getName());

        List<PurchaseLine> currentLines = repoPurchaseLine.obtainByPurchase(purchase.getId());
        manageLines(purchase, payload.lines(), currentLines);

        repoPurchase.save(purchase);

        PurchaseUpdatedEvent event = new PurchaseUpdatedEvent(purchase, getBookList());
        bus.dispatch(event);

        clearCache();

        return purchase;
    }
}
