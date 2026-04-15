package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ValueKindTest {
    @Test
    void shouldRunWhenParseString() {
        ValueKind kind = ValueKind.STRING;

        assertEquals("STRING", kind.toString());
        assertEquals("test", kind.parse("test"));
        assertEquals("123", kind.parse(123));
        assertEquals("123.56", kind.parse(123.56));
        assertEquals("true", kind.parse(true));
        assertEquals("", kind.parse(null));
        assertEquals("", kind.parse(new Object()));
        assertEquals("", kind.parse(new int[]{1, 2}));
        assertEquals("", kind.parse(new String[]{"test", "test2"}));
        assertEquals("", kind.parse(new ArrayList<String>()));
        assertNotEquals("", kind.parse(LocalDate.now()));
        assertNotEquals("", kind.parse(LocalDateTime.now()));
    }

    @Test
    void shouldRunWhenParseBoolean() {
        ValueKind kind = ValueKind.BOOLEAN;

        assertEquals("BOOLEAN", kind.toString());
        assertTrue((boolean) kind.parse(true));
        assertFalse((boolean) kind.parse(false));
        assertFalse((boolean) kind.parse("test"));
        assertTrue((boolean) kind.parse("true"));
        assertTrue((boolean) kind.parse("1"));
        assertTrue((boolean) kind.parse("yes"));
        assertTrue((boolean) kind.parse("on"));
        assertTrue((boolean) kind.parse("enabled"));
        assertTrue((boolean) kind.parse("active"));
        assertTrue((boolean) kind.parse(123));
        assertTrue((boolean) kind.parse(123.56));
        assertFalse((boolean) kind.parse(null));
        assertFalse((boolean) kind.parse(new Object()));
        assertFalse((boolean) kind.parse(new int[]{1, 2}));
        assertFalse((boolean) kind.parse(new String[]{"test", "test2"}));
        assertFalse((boolean) kind.parse(new ArrayList<String>()));
        assertFalse((boolean) kind.parse(LocalDate.now()));
        assertFalse((boolean) kind.parse(LocalDateTime.now()));
    }

    @Test
    void shouldRunWhenParseInteger() {
        ValueKind kind = ValueKind.INTEGER;

        assertEquals("INTEGER", kind.toString());
        assertEquals(1, (int) kind.parse(true));
        assertEquals(0, (int) kind.parse(false));
        assertEquals(0, (int) kind.parse("test"));
        assertEquals(1235, (int) kind.parse("1235"));
        assertEquals(123, (int) kind.parse(123));
        assertEquals(123, (int) kind.parse(123.56));
        assertEquals(0, (int) kind.parse(null));
        assertEquals(0, (int) kind.parse(new Object()));
        assertEquals(0, (int) kind.parse(new int[]{1, 2}));
        assertEquals(0, (int) kind.parse(new String[]{"test", "test2"}));
        assertEquals(0, (int) kind.parse(new ArrayList<String>()));
        assertEquals(0, (int) kind.parse(LocalDate.now()));
        assertEquals(0, (int) kind.parse(LocalDateTime.now()));
    }

    @Test
    void shouldRunWhenParseFloat() {
        ValueKind kind = ValueKind.FLOAT;

        assertEquals("FLOAT", kind.toString());
        assertEquals(1, (float) kind.parse(true));
        assertEquals(0, (float) kind.parse(false));
        assertEquals(0, (float) kind.parse("test"));
        assertEquals(1235f, (float) kind.parse("1235"));
        assertEquals(123f, (float) kind.parse(123));
        assertEquals(123.56f, (float) kind.parse(123.56));
        assertEquals(0, (float) kind.parse(null));
        assertEquals(0, (float) kind.parse(new Object()));
        assertEquals(0, (float) kind.parse(new int[]{1, 2}));
        assertEquals(0, (float) kind.parse(new String[]{"test", "test2"}));
        assertEquals(0, (float) kind.parse(new ArrayList<String>()));
        assertEquals(0, (float) kind.parse(LocalDate.now()));
        assertEquals(0, (float) kind.parse(LocalDateTime.now()));
    }

    @Test
    void shouldRunWhenParseDate() {
        ValueKind kind = ValueKind.DATE;

        assertEquals("DATE", kind.toString());
        assertNull(kind.parse(true));
        assertNull(kind.parse(false));
        assertNull(kind.parse("test"));
        assertInstanceOf(LocalDateTime.class, kind.parse("2024-01-01"));
        assertInstanceOf(LocalDateTime.class, kind.parse("2024-01-01 02:15:00"));
        assertInstanceOf(LocalDateTime.class, kind.parse("2024-01-01T02:15:00+01:00"));
        assertInstanceOf(LocalDateTime.class, kind.parse("2024-01-01T02:15:00.123"));
        assertNull(kind.parse(123));
        assertNull(kind.parse(123.56));
        assertNull(kind.parse(null));
        assertNull(kind.parse(new Object()));
        assertNull(kind.parse(new int[]{1, 2}));
        assertNull(kind.parse(new String[]{"test", "test2"}));
        assertNull(kind.parse(new ArrayList<String>()));
        assertInstanceOf(LocalDateTime.class, kind.parse(LocalDateTime.now()));
        assertInstanceOf(LocalDateTime.class, kind.parse(LocalDate.now()));
    }

    @Test
    void shouldRunWhenCheckIfDateIsShort() {
        assertFalse(ValueKind.isShortDate("invalid-date"));
        assertFalse(ValueKind.isShortDate("2024-01-01 02:15:00"));
        assertFalse(ValueKind.isShortDate("2024-01-01T02:15:00+01:00"));
        assertFalse(ValueKind.isShortDate("2024-01-01T02:15:00.123"));
        assertFalse(ValueKind.isShortDate("2024-13-01"));
        assertFalse(ValueKind.isShortDate("2024-01-32"));
        assertFalse(ValueKind.isShortDate("2024-00-01"));
        assertFalse(ValueKind.isShortDate("2024-01-00"));

        assertTrue(ValueKind.isShortDate("2024-01-01"));
    }

    @Test
    void shouldRunWhenParseList() {
        assertEquals(3, ValueKind.parseList("a,b,c").size());
        assertEquals(3, ValueKind.parseList("a, b, c").size());
        assertEquals(3, ValueKind.parseList("a b c").size());
        assertEquals(0, ValueKind.parseList("").size());
    }
}
