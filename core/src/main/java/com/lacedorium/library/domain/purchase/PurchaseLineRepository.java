package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.identity.IdentityRepository;

import java.util.List;

public interface PurchaseLineRepository extends IdentityRepository<PurchaseLine> {
    List<PurchaseLine> obtainByPurchase(String purchaseId);
    List<PurchaseLine> obtainByBook(String bookId);
    List<PurchaseLine> obtainByBook(String bookId, int limit);
}
