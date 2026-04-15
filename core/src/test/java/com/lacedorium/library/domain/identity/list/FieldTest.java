package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    @Test
    void shouldCreate() {
        Field field = new Field("alias");

        assertNotNull(field);
        assertEquals("alias", field.getAlias());
        assertEquals("alias", field.getFieldName());
        assertEquals(FilterType.WILDCARD, field.getType());
        assertEquals(ValueKind.STRING, field.getKind());
        assertTrue(field.getOptions().canSelect());
        assertTrue(field.getOptions().canFilter());
        assertTrue(field.getOptions().canSort());
    }

    @Test
    void shouldDefineFieldName() {
        Field field = new Field("alias", "fieldName");

        assertNotNull(field);
        assertEquals("alias", field.getAlias());
        assertEquals("fieldName", field.getFieldName());
        assertEquals(FilterType.WILDCARD, field.getType());
        assertEquals(ValueKind.STRING, field.getKind());
        assertTrue(field.getOptions().canSelect());
        assertTrue(field.getOptions().canFilter());
        assertTrue(field.getOptions().canSort());
    }

    @Test
    void shouldDefineTypeAndKind() {
        Field field = new Field("alias", FilterType.MATCH, ValueKind.INTEGER);

        assertNotNull(field);
        assertEquals("alias", field.getAlias());
        assertEquals("alias", field.getFieldName());
        assertEquals(FilterType.MATCH, field.getType());
        assertEquals(ValueKind.INTEGER, field.getKind());
        assertTrue(field.getOptions().canSelect());
        assertTrue(field.getOptions().canFilter());
        assertTrue(field.getOptions().canSort());
    }

    @Test
    void shouldDefineOptions() {
        Field field = new Field("alias", FieldOption.NO_FILTER, FieldOption.NO_SORT);

        assertNotNull(field);
        assertEquals("alias", field.getAlias());
        assertEquals("alias", field.getFieldName());
        assertEquals(FilterType.WILDCARD, field.getType());
        assertEquals(ValueKind.STRING, field.getKind());
        assertTrue(field.getOptions().canSelect());
        assertFalse(field.getOptions().canFilter());
        assertFalse(field.getOptions().canSort());
    }
}