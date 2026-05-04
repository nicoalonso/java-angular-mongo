package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseInvoice;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import com.lacedorium.library.domain.user.User;
import com.lacedorium.library.domain.user.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PurchaseCreate extends PurchaseMakeable {
    private final PurchaseRepository repoPurchase;
    private final UserRepository repoUser;
    private final DomainBus bus;

    public PurchaseCreate(
            PurchaseRepository repoPurchase,
            PurchaseLineRepository repoPurchaseLine,
            ProviderRepository repoProvider,
            BookRepository repoBook,
            UserRepository repoUser,
            DomainBus bus
    ) {
        super(repoBook, repoProvider, repoPurchaseLine);

        this.repoPurchase = repoPurchase;
        this.repoUser = repoUser;
        this.bus = bus;
    }

    public Purchase dispatch(@NonNull PurchaseCreatePayload payload) {
        check(payload.lines());
        checkAlreadyExists(payload);

        Provider provider = findProvider(payload.providerId());
        PurchaseInvoice invoice = makeInvoice(payload.invoice());
        User user = repoUser.obtainUser();

        Purchase purchase = new Purchase(
                provider,
                payload.getPurchasedAt(),
                invoice,
                user.getName()
        );
        repoPurchase.save(purchase);

        manageLines(purchase, payload.lines());

        PurchaseCreatedEvent event = new PurchaseCreatedEvent(purchase, getBookList());
        bus.dispatch(event);

        clearCache();

        return purchase;
    }

    private void checkAlreadyExists(@NonNull PurchaseCreatePayload payload) {
        if (repoPurchase.obtainByProviderAndNumber(payload.providerId(), payload.invoice().number()).isPresent()) {
            throw new PurchaseAlreadyExistsException();
        }
    }
}
