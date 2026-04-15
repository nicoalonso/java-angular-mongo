package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListResultTest {
    @Test
    void shouldReturnEmptyResultWhenNoData() {
        List<String> items = List.of();

        ListResult<String> result = new ListResult<>(items);

        assertTrue(result.getItems().isEmpty());
        assertEquals(0, result.getPagination().getTotal());
        assertEquals(1, result.getPagination().getPage());
        assertEquals(10, result.getPagination().getRowsPerPage());
    }

    @Test
    void shouldRunWhenCreate() {
        List<String> items = List.of(new String[] {"item1", "item2"});
        Pagination pagination = new Pagination(2, 10, 20);

        ListResult<String> result = new ListResult<>(items, pagination);

        assertFalse(result.getItems().isEmpty());
        assertEquals(2, result.getPagination().getTotal());
        assertEquals(10, result.getPagination().getPage());
        assertEquals(20, result.getPagination().getRowsPerPage());
    }
}
