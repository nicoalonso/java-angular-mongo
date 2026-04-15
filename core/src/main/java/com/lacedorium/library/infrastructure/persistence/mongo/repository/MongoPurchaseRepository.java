package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.PurchaseDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoPurchaseRepository
        extends MongoRepository<Purchase, PurchaseDocument>
        implements PurchaseRepository {
    public MongoPurchaseRepository(MongoTemplate client) {
        super(client, Purchase.class, PurchaseDocument.class);
    }

    @Override
    public Optional<Purchase> obtainByProviderAndNumber(String providerId, String invoiceNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("provider.id", providerId);
        params.put("invoice.number", invoiceNumber);

        return findOneBy(params);
    }

    @Override
    public List<Purchase> obtainByProvider(String providerId) {
        return obtainByProvider(providerId, 0);
    }

    @Override
    public List<Purchase> obtainByProvider(String providerId, int limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("provider.id", providerId);

        return findBy(params, limit);
    }
}
