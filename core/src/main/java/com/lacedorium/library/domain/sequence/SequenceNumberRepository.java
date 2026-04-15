package com.lacedorium.library.domain.sequence;

import org.jspecify.annotations.NonNull;

public interface SequenceNumberRepository {
    @NonNull SequenceNumber obtainByType(@NonNull SequenceType type);
    @NonNull SequenceNumber nextNumber(@NonNull SequenceType type);
}
