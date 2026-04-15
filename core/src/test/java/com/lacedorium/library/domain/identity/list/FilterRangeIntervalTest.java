package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterRangeIntervalTest {
    @Test
    void shouldFalseWhenValueIsNotAInterval() {
        FilterRangeInterval.CheckResult result = FilterRangeInterval.check("invalid");

        assertFalse(result.name().isEmpty());
        assertEquals(FilterRangeInterval.EMPTY, result.interval());
        assertEquals("empty", result.interval().toString());
    }

    @Test
    void shouldRangeFromWhenValueHasPrefix() {
        FilterRangeInterval.CheckResult result = FilterRangeInterval.check("fromName");

        assertEquals("name", result.name());
        assertEquals(FilterRangeInterval.FROM, result.interval());
        assertEquals("from", result.interval().toString());
    }

    @Test
    void shouldRangeToWhenValueHasPrefix() {
        FilterRangeInterval.CheckResult result = FilterRangeInterval.check("toName");

        assertEquals("name", result.name());
        assertEquals(FilterRangeInterval.TO, result.interval());
        assertEquals("to", result.interval().toString());
    }

    @Test
    void shouldReturnEmptyIntervalForEmptyName() {
        FilterRangeInterval.CheckResult result = FilterRangeInterval.check("");

        assertTrue(result.name().isEmpty());
        assertEquals(FilterRangeInterval.EMPTY, result.interval());
    }
}