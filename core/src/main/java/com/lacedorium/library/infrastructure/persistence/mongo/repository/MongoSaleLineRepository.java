package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.domain.sale.SaleLineRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.SaleLineDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MongoSaleLineRepository
        extends MongoRepository<SaleLine, SaleLineDocument>
        implements SaleLineRepository {
    public MongoSaleLineRepository(MongoTemplate client) {
        super(client, SaleLine.class, SaleLineDocument.class);
    }

    @Override
    public List<SaleLine> obtainBySale(String saleId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("sale", saleId);

        return findBy(params);
    }

    @Override
    public List<SaleLine> obtainByBook(String bookId) {
        return obtainByBook(bookId, 0);
    }

    @Override
    public List<SaleLine> obtainByBook(String bookId, Integer limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("book.id", bookId);

        return findBy(params, limit);
    }
}
