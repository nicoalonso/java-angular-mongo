package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.BorrowDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoBorrowRepository
        extends MongoRepository<Borrow, BorrowDocument>
        implements BorrowRepository {
    public MongoBorrowRepository(MongoTemplate client) {
        super(client, Borrow.class, BorrowDocument.class);
    }

    @Override
    public Optional<Borrow> obtainByNumber(String number) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("number", number);

        return findOneBy(params);
    }

    @Override
    public List<Borrow> obtainByCustomer(String customerId) {
        return obtainByCustomer(customerId, 0);
    }

    @Override
    public List<Borrow> obtainByCustomer(String customerId, Integer limit) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("customer.id", customerId);

        return findBy(params, limit);
    }

    @Override
    public List<Borrow> obtainByOverdue() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("returned", false);

        Criteria dueDateCriteria = Criteria.where("dueDate").lt(LocalDateTime.now());
        params.put("dueDate", dueDateCriteria);

        return findBy(params);
    }
}
