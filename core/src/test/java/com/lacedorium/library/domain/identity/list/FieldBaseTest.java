package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldBaseTest {
    @Test
    void shouldCreate() {
        FieldBase field = new FieldBase("name");

        assertNotNull(field);
        assertEquals("name", field.getName());
        assertEquals("name", field.getAlias());
    }

    @Test
    void shouldMapField() {
        FieldBase field = new FieldBase("name");
        Field mappedField = new Field("mappedName");

        field.mapping(mappedField);

        assertEquals("mappedName", field.getName());
        assertEquals("name", field.getAlias());
    }

    @Test
    void shouldIsEqual() {
        FieldBase field = new FieldBase("name");

        assertTrue(field.is("name"));
        assertFalse(field.is("wrong"));
    }
}