package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.EditorialRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.EditorialDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class MongoEditorialRepository
        extends MongoRepository<Editorial, EditorialDocument>
        implements EditorialRepository {
    public MongoEditorialRepository(MongoTemplate client) {
        super(client, Editorial.class, EditorialDocument.class);
    }

    @Override
    public Optional<Editorial> obtainByName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);

        return findOneBy(params);
    }
}
