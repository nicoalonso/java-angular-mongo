package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldMapTest {
    @Test
    void shouldCreateAsEmpty() {
        FieldMap fieldMap = new FieldMap(List.of());

        FieldBase filter = new FieldBase("any");

        assertTrue(fieldMap.getFields().isEmpty());
        assertFalse(fieldMap.hasField("field1"));
        assertFalse(fieldMap.canSelect(filter));
        assertFalse(fieldMap.canFilter(filter));
        assertFalse(fieldMap.canSort(filter));
    }

    @Test
    void shouldCreate() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("field1", "value1"),
                new Field("field2", "value2")
        ));

        assertFalse(fieldMap.getFields().isEmpty());
        assertEquals(2, fieldMap.getFields().size());
        assertTrue(fieldMap.hasField("field1"));
        assertTrue(fieldMap.hasField("field2"));
    }

    @Test
    void shouldCannotSelectWhenAliasNotFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("field1", "value1"),
                new Field("field2", "value2")
        ));

        FieldBase field = new FieldBase("field3");

        assertFalse(fieldMap.canSelect(field));
    }

    @Test
    void shouldCannotSelectWhenAliasFoundAndDisableSelect() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "value1", FieldOption.NO_SELECT)
        ));

        FieldBase field = new FieldBase("number");

        assertFalse(fieldMap.canSelect(field));
    }

    @Test
    void shouldCanSelectAndUpdateWhenAliasFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number")
        ));

        FieldBase field = new FieldBase("number");

        assertTrue(fieldMap.canSelect(field));
        assertEquals("number", field.getAlias());
        assertEquals("invoice.number", field.getName());
    }

    @Test
    void shouldCannotFilterWhenAliasNotFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number")
        ));

        FilterField filter = new FilterField("test", "value1");

        assertFalse(fieldMap.canFilter(filter));
    }

    @Test
    void shouldCannotFilterWhenDisableFilter() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number", FieldOption.NO_FILTER)
        ));

        FilterField filter = new FilterField("number", "value1");

        assertFalse(fieldMap.canFilter(filter));
    }

    @Test
    void shouldCanFilterAndUpdateWhenAliasFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number")
        ));

        FilterField filter = new FilterField("number", "value1");

        assertTrue(fieldMap.canFilter(filter));
        assertEquals("invoice.number", filter.getName());
        assertEquals("number", filter.getAlias());
    }

    @Test
    void shouldCanFilterAndUpdateFieldTypeWhenAliasFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("date", "createdAt", FilterType.RANGE, ValueKind.DATE)
        ));

        FilterField filter = new FilterField("date", "2023-01-01");

        assertTrue(fieldMap.canFilter(filter));
        assertEquals("date", filter.getAlias());
        assertEquals("createdAt", filter.getName());
        assertEquals(FilterType.RANGE, filter.getType());
        assertEquals(ValueKind.DATE, filter.getKind());
        assertInstanceOf(FilterRange.class, filter.getValue());
    }

    @Test
    void shouldCannotSortWhenAliasNotFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number")
        ));

        SortField sort = new SortField("test");

        assertFalse(fieldMap.canSort(sort));
    }

    @Test
    void shouldCannotSortWhenAliasFoundAndDisableSort() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number", FieldOption.NO_SORT)
        ));

        SortField sort = new SortField("number");

        assertFalse(fieldMap.canSort(sort));
    }

    @Test
    void shouldCanSortWhenAliasFound() {
        FieldMap fieldMap = new FieldMap(List.of(
                new Field("number", "invoice.number")
        ));

        SortField sort = new SortField("number");

        assertTrue(fieldMap.canSort(sort));
    }
}
