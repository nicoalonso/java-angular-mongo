package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortFieldTest {
    @Test
    void shouldCreate() {
        SortField field = new SortField("name", SortDirection.ASC);

        assertNotNull(field);
        assertEquals("name", field.getName());
        assertEquals(SortDirection.ASC, field.getDirection());
        assertTrue(field.isAscending());
        assertFalse(field.isDescending());
    }

    @Test
    void shouldValidWhenCreateFromStringWithoutDirection() {
        SortField field = SortField.fromString("name");

        assertNotNull(field);
        assertEquals("name", field.getName());
        assertEquals(SortDirection.ASC, field.getDirection());
        assertTrue(field.isAscending());
        assertFalse(field.isDescending());
    }

    @Test
    void shouldAscWhenCreateFromStringWithDirectionAsc() {
        SortField field = SortField.fromString("+name");

        assertNotNull(field);
        assertEquals("name", field.getName());
        assertEquals(SortDirection.ASC, field.getDirection());
        assertTrue(field.isAscending());
        assertFalse(field.isDescending());
    }

    @Test
    void shouldAscWhenCreateFromStringWithDirectionDesc() {
        SortField field = SortField.fromString("-name");

        assertNotNull(field);
        assertEquals("name", field.getName());
        assertEquals(SortDirection.DESC, field.getDirection());
        assertFalse(field.isAscending());
        assertTrue(field.isDescending());
    }

    @Test
    void shouldRunWhenUpdateByMapping() {
        SortField sortField = new SortField("number");
        Field field = new Field("number", "invoice.number");
        sortField.mapping(field);

        assertEquals("number", sortField.getAlias());
        assertEquals("invoice.number", sortField.getName());
    }
}