package com.lacedorium.library.domain.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceNumberTest {
    @Test
    void shouldCreate() {
        SequenceNumber sequenceNumber = new SequenceNumber(SequenceType.MEMBERSHIP);

        assertEquals(SequenceType.MEMBERSHIP, sequenceNumber.getType());
        assertEquals("SN", sequenceNumber.getPrefix());
        assertEquals(0, sequenceNumber.getNumber());
        assertEquals("SN00000", sequenceNumber.toString());
    }

    @Test
    void shouldRunWhenIncrement() {
        SequenceNumber sequenceNumber = new SequenceNumber(SequenceType.MEMBERSHIP);

        sequenceNumber.next();
        assertEquals(1, sequenceNumber.getNumber());
        assertEquals("SN00001", sequenceNumber.toString());

        sequenceNumber.next();
        assertEquals(2, sequenceNumber.getNumber());
        assertEquals("SN00002", sequenceNumber.toString());
    }
}
