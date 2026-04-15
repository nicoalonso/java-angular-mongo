package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.SequenceNumberDocument;
import org.jspecify.annotations.NonNull;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MongoSequenceNumberRepository
        extends MongoRepository<SequenceNumber, SequenceNumberDocument>
        implements SequenceNumberRepository {
    public MongoSequenceNumberRepository(MongoTemplate client) {
        super(client, SequenceNumber.class, SequenceNumberDocument.class);
    }

    @Override
    public @NonNull SequenceNumber obtainByType(@NonNull SequenceType type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type.toString());

        SequenceNumber number = findOneBy(params).orElse(new SequenceNumber(type));
        return number.next();
    }

    @Override
    public @NonNull SequenceNumber nextNumber(@NonNull SequenceType type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type.toString());

        SequenceNumber number = findOneBy(params).orElse(new SequenceNumber(type));
        number.next();

        save(number);

        return number;
    }
}
