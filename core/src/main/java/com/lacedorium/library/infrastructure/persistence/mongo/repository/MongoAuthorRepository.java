package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.AuthorRepository;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.AuthorDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class MongoAuthorRepository extends MongoRepository<Author, AuthorDocument> implements AuthorRepository {
    public MongoAuthorRepository(MongoTemplate client) {
        super(client, Author.class, AuthorDocument.class);
    }

    @Override
    public Optional<Author> obtainByName(String name) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);

        return findOneBy(params);
    }
}
