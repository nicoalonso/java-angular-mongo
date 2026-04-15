package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends IdentityRepository<Purchase> {
    Optional<Purchase> obtainByProviderAndNumber(String providerId, String invoiceNumber);
    List<Purchase> obtainByProvider(String providerId);
    List<Purchase> obtainByProvider(String providerId, int limit);
}
