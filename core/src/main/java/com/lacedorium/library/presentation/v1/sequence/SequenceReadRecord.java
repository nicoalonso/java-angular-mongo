package com.lacedorium.library.presentation.v1.sequence;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import org.jspecify.annotations.NonNull;

public record SequenceReadRecord(
        String number
) {
    public static @NonNull SequenceReadRecord make(@NonNull SequenceNumber number) {
        return new SequenceReadRecord(number.format());
    }
}
