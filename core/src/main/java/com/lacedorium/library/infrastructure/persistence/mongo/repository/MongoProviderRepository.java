package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.ProviderDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class MongoProviderRepository extends MongoRepository<Provider, ProviderDocument> implements ProviderRepository {
    public MongoProviderRepository(MongoTemplate client) {
        super(client, Provider.class, ProviderDocument.class);
    }

    @Override
    public Optional<Provider> obtainByName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);

        return findOneBy(params);
    }
}
