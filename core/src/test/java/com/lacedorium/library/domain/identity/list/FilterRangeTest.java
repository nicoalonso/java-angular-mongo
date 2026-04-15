package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FilterRangeTest {
    @Test
    void shouldCreateAsEmpty() {
        FilterRange range = new FilterRange(null, null);

        assertNotNull(range);
        assertNull(range.getFrom());
        assertNull(range.getTo());
        assertFalse(range.hasValue());
        assertFalse(range.hasFrom());
        assertFalse(range.hasTo());
        assertEquals(ValueKind.STRING, range.getKind());
    }

    @Test
    void shouldHasValueWhenHasOneValue() {
        FilterRange range = new FilterRange("test", null);

        assertTrue(range.hasValue());
        assertTrue(range.hasFrom());
        assertFalse(range.hasTo());
    }

    @Test
    void shouldEmptyWhenEmptyAndParseAsDate() {
        FilterRange range = new FilterRange("", "");

        range.parse(ValueKind.DATE);

        assertFalse(range.hasValue());
        assertFalse(range.hasFrom());
        assertFalse(range.hasTo());
        assertEquals(ValueKind.DATE, range.getKind());
    }

    @Test
    void shouldRunWhenCreateWithValues() {
        FilterRange range = new FilterRange("10", "50");

        assertTrue(range.hasValue());
        assertTrue(range.hasFrom());
        assertTrue(range.hasTo());
        assertEquals(ValueKind.STRING, range.getKind());
        assertEquals("10", range.getFrom());
        assertEquals("50", range.getTo());
    }

    @Test
    void shouldRunWhenParseAsInteger() {
        FilterRange range = new FilterRange("10", "50");

        range.parse(ValueKind.INTEGER);

        assertTrue(range.hasValue());
        assertTrue(range.hasFrom());
        assertTrue(range.hasTo());
        assertEquals(ValueKind.INTEGER, range.getKind());
        assertEquals(10, range.getFrom());
        assertEquals(50, range.getTo());
    }

    @Test
    void shouldRunWhenParseAsFloat() {
        FilterRange range = new FilterRange("10.5", "50.5");

        range.parse(ValueKind.FLOAT);

        assertTrue(range.hasValue());
        assertTrue(range.hasFrom());
        assertTrue(range.hasTo());
        assertEquals(ValueKind.FLOAT, range.getKind());
        assertEquals(10.5f, range.getFrom());
        assertEquals(50.5f, range.getTo());
    }

    @Test
    void shouldRunWhenParseAsDate() {
        FilterRange range = new FilterRange("2024-01-01", "2024-01-31");

        range.parse(ValueKind.DATE);

        assertTrue(range.hasValue());
        assertTrue(range.hasFrom());
        assertTrue(range.hasTo());
        assertEquals(ValueKind.DATE, range.getKind());
        assertInstanceOf(LocalDateTime.class, range.getFrom());
        assertInstanceOf(LocalDateTime.class, range.getTo());
        assertEquals("2024-01-01T00:00", range.getFrom().toString());
        assertEquals("2024-01-31T23:59:59", range.getTo().toString());
    }

    @Test
    void shouldRunWhenParseWrongDate() {
        FilterRange range = new FilterRange("invalid-date", "2024-01-31");

        range.parse(ValueKind.DATE);

        assertTrue(range.hasValue());
        assertFalse(range.hasFrom());
        assertTrue(range.hasTo());
        assertEquals(ValueKind.DATE, range.getKind());
        assertNull(range.getFrom());
        assertInstanceOf(LocalDateTime.class, range.getTo());
        assertEquals("2024-01-31T23:59:59", range.getTo().toString());
    }

    @Test
    void shouldModifyValues() {
        FilterRange range = new FilterRange("10", "50");

        range.modify("20", "40");

        assertEquals("20", range.getFrom());
        assertEquals("40", range.getTo());
    }

    @Test
    void shouldNotParseWhenHasValues() {
        FilterRange range = new FilterRange("10", "50");

        range.modify(null, null);
        range.modify(20, 40);

        range.parse(ValueKind.STRING);

        assertEquals(20, range.getFrom());
        assertEquals(40, range.getTo());
    }
}
