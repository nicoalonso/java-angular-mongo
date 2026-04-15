package com.lacedorium.library.domain.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTypeTest {
    @Test
    void shouldCreateFromString() {
        assertEquals(SequenceType.MEMBERSHIP, SequenceType.fromString("membership"));
        assertEquals(SequenceType.SALE, SequenceType.fromString("sale"));
        assertEquals(SequenceType.BORROW, SequenceType.fromString("borrow"));
        assertNull(SequenceType.fromString("wrong"));
    }

    @Test
    void shouldRunWhenGetPrefix() {
        assertEquals("SN", SequenceType.MEMBERSHIP.getPrefix());
        assertEquals("F-", SequenceType.SALE.getPrefix());
        assertEquals("P-", SequenceType.BORROW.getPrefix());
    }

    @Test
    void shouldRunWhenToString() {
        assertEquals("membership", SequenceType.MEMBERSHIP.toString());
    }
}