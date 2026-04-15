package com.lacedorium.library.application.sequence.simulator;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.domain.sequence.SequenceNumberRepository;
import com.lacedorium.library.domain.sequence.SequenceType;
import com.lacedorium.library.domain.sequence.exception.InvalidSequenceTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceSimulate {
    private final SequenceNumberRepository repoSequenceNumber;

    public SequenceNumber dispatch(String type) {
        SequenceType sequenceType = SequenceType.fromString(type);
        if (sequenceType == null) {
            throw new InvalidSequenceTypeException();
        }

        return repoSequenceNumber.obtainByType(sequenceType);
    }
}
