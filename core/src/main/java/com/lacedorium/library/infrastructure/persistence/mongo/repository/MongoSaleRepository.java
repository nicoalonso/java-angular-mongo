package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.SaleDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoSaleRepository
        extends MongoRepository<Sale, SaleDocument>
        implements SaleRepository {
    public MongoSaleRepository(MongoTemplate client) {
        super(client, Sale.class, SaleDocument.class);
    }

    @Override
    public Optional<Sale> obtainByNumber(String number) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("number", number);

        return findOneBy(params);
    }

    @Override
    public List<Sale> obtainByCustomer(String customerId) {
        return obtainByCustomer(customerId, 0);
    }

    @Override
    public List<Sale> obtainByCustomer(String customerId, Integer limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("customer.id", customerId);

        return findBy(params, limit);
    }
}
