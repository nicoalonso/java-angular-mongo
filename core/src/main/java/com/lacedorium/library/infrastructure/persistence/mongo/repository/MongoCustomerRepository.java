package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.CustomerRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.CustomerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class MongoCustomerRepository
        extends MongoRepository<Customer, CustomerDocument>
        implements CustomerRepository {
    public MongoCustomerRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Customer.class, CustomerDocument.class);
    }

    @Override
    public Optional<Customer> obtainByName(String name, String surname) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);

        return findOneBy(params);
    }

    @Override
    public Optional<Customer> obtainByNumber(String number) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("membership.number", number);

        return findOneBy(params);
    }
}
