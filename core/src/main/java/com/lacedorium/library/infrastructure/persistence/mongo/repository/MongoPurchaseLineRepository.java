package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.PurchaseLineDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MongoPurchaseLineRepository
        extends MongoRepository<PurchaseLine, PurchaseLineDocument>
        implements PurchaseLineRepository {
    public MongoPurchaseLineRepository(MongoTemplate client) {
        super(client, PurchaseLine.class, PurchaseLineDocument.class);
    }

    @Override
    public List<PurchaseLine> obtainByPurchase(String purchaseId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("purchase", purchaseId);

        return findBy(params);
    }

    @Override
    public List<PurchaseLine> obtainByBook(String bookId) {
        return obtainByBook(bookId, 0);
    }

    @Override
    public List<PurchaseLine> obtainByBook(String bookId, int limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("book.id", bookId);

        return findBy(params, limit);
    }
}
