package com.lacedorium.library.presentation.v1.sequence;

import com.lacedorium.library.domain.sequence.SequenceNumber;
import com.lacedorium.library.presentation.v1.identity.Result;

public class SequenceReadView extends Result<SequenceReadRecord, SequenceNumber> {
    public SequenceReadView(SequenceNumber number) {
        super(number, SequenceReadRecord::make);
    }
}
