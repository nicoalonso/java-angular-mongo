package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterFieldTest {
    @Test
    void shouldCreate() {
        FilterField field = new FilterField("test", "value1");

        assertEquals("test", field.getAlias());
        assertEquals("test", field.getName());
        assertEquals("value1", field.getRaw());
        assertEquals("value1", field.getValue());
        assertTrue(field.hasValue());
        assertEquals(FilterType.WILDCARD, field.getType());
        assertEquals(ValueKind.STRING, field.getKind());
        assertFalse(field.isRange());
        assertFalse(field.isList());
    }

    @Test
    void shouldHasNotValueWhenIsEmpty() {
        FilterField field = new FilterField("test", "");

        assertFalse(field.hasValue());
    }

    @Test
    void shouldHasNotValueWhenIsNull() {
        FilterField field = new FilterField("test", "");
        field.changeValue(null);

        assertFalse(field.hasValue());
    }

    @Test
    void shouldChangeValue() {
        FilterField field = new FilterField("test", "value1");

        field.changeValue(11, FilterType.FUZZY, ValueKind.INTEGER);

        assertEquals("value1", field.getRaw());
        assertTrue(field.hasValue());
        assertEquals(11, field.getValue());
        assertEquals(FilterType.FUZZY, field.getType());
        assertEquals(ValueKind.INTEGER, field.getKind());
    }

    @Test
    void shouldNullWhenIntervalIsNull() {
        FilterField field = new FilterField("test", "1234");

        field.range(FilterRangeInterval.EMPTY, "5678");

        assertFalse(field.getValue() instanceof FilterRange);
        assertEquals("1234", field.getValue());
    }

    @Test
    void makeWhenRangeTo() {
        FilterField field = new FilterField("test", "1234");
        field.changeValue("1234", FilterType.RANGE);

        field.range(FilterRangeInterval.TO, "5678");

        assertInstanceOf(FilterRange.class, field.getValue());
        assertTrue(field.isRange());
        assertTrue(field.hasValue());
        assertFalse(((FilterRange) field.getValue()).hasFrom());
        assertTrue(((FilterRange) field.getValue()).hasTo());
        assertEquals("5678", ((FilterRange) field.getValue()).getTo());
    }

    @Test
    void makeWhenRangeEmptyTo() {
        FilterField field = new FilterField("test", "");
        field.changeValue("", FilterType.RANGE);

        field.range(FilterRangeInterval.TO, "");

        assertInstanceOf(FilterRange.class, field.getValue());
        assertTrue(field.isRange());
        assertFalse(field.hasValue());
        assertFalse(((FilterRange) field.getValue()).hasValue());
    }

    @Test
    void makeWhenRangeFrom() {
        FilterField field = new FilterField("test", "1234");
        field.changeValue("1234", FilterType.RANGE);

        field.range(FilterRangeInterval.FROM, "5678");

        assertInstanceOf(FilterRange.class, field.getValue());
        assertTrue(field.isRange());
        assertTrue(field.hasValue());
        assertTrue(((FilterRange) field.getValue()).hasFrom());
        assertFalse(((FilterRange) field.getValue()).hasTo());
        assertEquals("5678", ((FilterRange) field.getValue()).getFrom());
    }

    @Test
    void makeUpdateWhenRangeTo() {
        FilterField field = new FilterField("test", "1234");
        FilterRange range = new FilterRange("1234", null);
        field.changeValue(range, FilterType.RANGE);

        field.range(FilterRangeInterval.TO, "5678");

        assertInstanceOf(FilterRange.class, field.getValue());
        assertTrue(field.isRange());
        assertTrue(field.hasValue());
        assertTrue(((FilterRange) field.getValue()).hasFrom());
        assertTrue(((FilterRange) field.getValue()).hasTo());
        assertEquals("1234", ((FilterRange) field.getValue()).getFrom());
        assertEquals("5678", ((FilterRange) field.getValue()).getTo());
    }

    @Test
    void makeUpdateWhenRangeFrom() {
        FilterField field = new FilterField("test", "1234");
        FilterRange range = new FilterRange(null, "1234");
        field.changeValue(range, FilterType.RANGE);

        field.range(FilterRangeInterval.FROM, "5678");

        assertInstanceOf(FilterRange.class, field.getValue());
        assertTrue(field.isRange());
        assertTrue(field.hasValue());
        assertTrue(((FilterRange) field.getValue()).hasFrom());
        assertTrue(((FilterRange) field.getValue()).hasTo());
        assertEquals("5678", ((FilterRange) field.getValue()).getFrom());
        assertEquals("1234", ((FilterRange) field.getValue()).getTo());
    }

    @Test
    void shouldRunWhenMappedAsBoolean() {
        Field field = new Field("test", FilterType.MATCH, ValueKind.BOOLEAN);
        FilterField filterField = new FilterField("test", "TRUE");

        filterField.mapping(field);

        assertTrue(((Boolean) filterField.getValue()));
        assertEquals(FilterType.MATCH, filterField.getType());
        assertEquals(ValueKind.BOOLEAN, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsBooleanAsFalse() {
        Field field = new Field("test", FilterType.MATCH, ValueKind.BOOLEAN);
        FilterField filterField = new FilterField("test", "FALSE");

        filterField.mapping(field);

        assertFalse(((Boolean) filterField.getValue()));
        assertEquals(FilterType.MATCH, filterField.getType());
        assertEquals(ValueKind.BOOLEAN, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsBooleanAsWrongString() {
        Field field = new Field("test", FilterType.MATCH, ValueKind.BOOLEAN);
        FilterField filterField = new FilterField("test", "WRONG");

        filterField.mapping(field);

        assertFalse(((Boolean) filterField.getValue()));
        assertEquals(FilterType.MATCH, filterField.getType());
        assertEquals(ValueKind.BOOLEAN, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsInteger() {
        Field field = new Field("test", FilterType.MATCH, ValueKind.INTEGER);
        FilterField filterField = new FilterField("test", "123");

        filterField.mapping(field);

        assertEquals(123, filterField.getValue());
        assertEquals(FilterType.MATCH, filterField.getType());
        assertEquals(ValueKind.INTEGER, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsZeroInteger() {
        Field field = new Field("test", FilterType.MATCH, ValueKind.INTEGER);
        FilterField filterField = new FilterField("test", "0");

        filterField.mapping(field);

        assertEquals(0, filterField.getValue());
        assertEquals(FilterType.MATCH, filterField.getType());
        assertEquals(ValueKind.INTEGER, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsDate() {
        Field field = new Field("test", ValueKind.DATE);
        FilterField filterField = new FilterField("test", "2025-01-01");

        filterField.mapping(field);

        assertInstanceOf(LocalDateTime.class, filterField.getValue());
        assertEquals(ValueKind.DATE, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsRangeDate() {
        Field field = new Field("test", FilterType.RANGE, ValueKind.DATE);
        FilterField filterField = new FilterField("test", "2025-01-01");

        filterField.mapping(field);

        assertInstanceOf(FilterRange.class, filterField.getValue());
        assertEquals(FilterType.RANGE, filterField.getType());
        assertEquals(ValueKind.DATE, filterField.getKind());
        assertTrue(filterField.hasValue());
    }

    @Test
    void shouldRunWhenMappedAsDateAndHasRange() {
        FilterField filterField = new FilterField("test", "2025-01-01");
        filterField.range(FilterRangeInterval.FROM, "2025-01-01");
        filterField.range(FilterRangeInterval.TO, "2025-12-31");

        Field field = new Field("test", FilterType.RANGE, ValueKind.DATE);
        filterField.mapping(field);

        assertInstanceOf(FilterRange.class, filterField.getValue());
        assertEquals(FilterType.RANGE, filterField.getType());
        assertEquals(ValueKind.DATE, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertTrue(((FilterRange) filterField.getValue()).hasFrom());
        assertTrue(((FilterRange) filterField.getValue()).hasTo());
        assertEquals("2025-01-01T00:00", ((FilterRange) filterField.getValue()).getFrom().toString());
        assertEquals("2025-12-31T23:59:59", ((FilterRange) filterField.getValue()).getTo().toString());
    }

    @Test
    void shouldRunWhenMappedAsList() {
        FilterField filterField = new FilterField("test", "value1,value2,value3");

        Field field = new Field("test", FilterType.IN);
        filterField.mapping(field);

        assertInstanceOf(List.class, filterField.getValue());
        assertEquals(FilterType.IN, filterField.getType());
        assertEquals(ValueKind.STRING, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertEquals(3, ((List<?>) filterField.getValue()).size());
    }

    @Test
    void shouldRunWhenMappedAsListInteger() {
        FilterField filterField = new FilterField("test", "1,2,3");

        Field field = new Field("test", FilterType.IN, ValueKind.INTEGER);
        filterField.mapping(field);

        assertInstanceOf(List.class, filterField.getValue());
        assertEquals(FilterType.IN, filterField.getType());
        assertEquals(ValueKind.INTEGER, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertEquals(3, ((List<?>) filterField.getValue()).size());
    }

    @Test
    void shouldRunWhenMappedAsListFloat() {
        FilterField filterField = new FilterField("test", "1.1,2.2,3.3");

        Field field = new Field("test", FilterType.IN, ValueKind.FLOAT);
        filterField.mapping(field);

        assertInstanceOf(List.class, filterField.getValue());
        assertEquals(FilterType.IN, filterField.getType());
        assertEquals(ValueKind.FLOAT, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertEquals(3, ((List<?>) filterField.getValue()).size());
    }

    @Test
    void shouldRunWhenMappedAsListBool() {
        FilterField filterField = new FilterField("test", "true,false,true");

        Field field = new Field("test", FilterType.IN, ValueKind.BOOLEAN);
        filterField.mapping(field);

        assertInstanceOf(List.class, filterField.getValue());
        assertEquals(FilterType.IN, filterField.getType());
        assertEquals(ValueKind.BOOLEAN, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertEquals(3, ((List<?>) filterField.getValue()).size());
    }

    @Test
    void shouldRunWhenMappedAsListDate() {
        FilterField filterField = new FilterField("test", "2023-01-01,2023-02-01,invalid");

        Field field = new Field("test", FilterType.IN, ValueKind.DATE);
        filterField.mapping(field);

        assertInstanceOf(List.class, filterField.getValue());
        assertEquals(FilterType.IN, filterField.getType());
        assertEquals(ValueKind.DATE, filterField.getKind());
        assertTrue(filterField.hasValue());
        assertEquals(2, ((List<?>) filterField.getValue()).size());
    }
}
