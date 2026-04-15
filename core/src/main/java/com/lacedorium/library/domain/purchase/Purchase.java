package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.identity.Entity;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderDescriptor;
import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Purchase extends Entity {
    private ProviderDescriptor provider;
    private LocalDateTime purchasedAt;
    private PurchaseInvoice invoice;

    public Purchase(
            @NonNull Provider provider,
            @NonNull LocalDateTime purchasedAt,
            PurchaseInvoice invoice,
            String createdBy
    ) {
        super(createdBy);

        check(purchasedAt);

        this.provider = provider.getDescriptor();
        this.purchasedAt = purchasedAt;
        this.invoice = invoice;
    }

    public void modify(
            @NonNull Provider provider,
            @NonNull LocalDateTime purchasedAt,
            PurchaseInvoice invoice,
            String updatedBy
    ) {
        check(purchasedAt);

        this.provider = provider.getDescriptor();
        this.purchasedAt = purchasedAt;
        this.invoice = invoice;
        updated(updatedBy);
    }

    private void check(@NonNull LocalDateTime purchasedAt) {
        LocalDateTime limit = LocalDateTime.now().plusDays(1);
        if (purchasedAt.isAfter(limit)) {
            throw new InvalidPurchaseDateException();
        }
    }
}
