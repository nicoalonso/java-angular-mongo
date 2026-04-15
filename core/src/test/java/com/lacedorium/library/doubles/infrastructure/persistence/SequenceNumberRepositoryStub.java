package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.doubles.Exceptionable;
import lombok.SneakyThrows;
import org.jspecify.annotations.NonNull;

public class SequenceNumberRepositoryStub extends Exceptionable implements SequenceNumberRepository {
    @SneakyThrows
    @Override
    public @NonNull SequenceNumber obtainByType(@NonNull SequenceType type) {
        throwException();

        SequenceNumber sequence = new SequenceNumber(type);
        sequence.next();
        return sequence;
    }

    @SneakyThrows
    @Override
    public @NonNull SequenceNumber nextNumber(@NonNull SequenceType type) {
        throwException();

        SequenceNumber sequence = new SequenceNumber(type);
        sequence.next();
        return sequence;
    }
}
