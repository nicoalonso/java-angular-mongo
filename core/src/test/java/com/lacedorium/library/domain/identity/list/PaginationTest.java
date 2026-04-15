package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {
    @Test
    void shouldFailWhenTotalIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Pagination(-1, 10, 5));
    }

    @Test
    void shouldFailWhenLimitIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Pagination(10, -1, 5));
    }

    @Test
    void shouldCalculateTotalPagesCorrectly() {
        Pagination pagination = new Pagination(100, 1, 10);

        assertEquals(10, pagination.getTotalPages());
        assertEquals(1, pagination.getPage());
        assertEquals(100, pagination.getTotal());
        assertEquals(10, pagination.getRowsPerPage());
    }

    @Test
    void shouldCalculateTotalPagesWithZeroTotal() {
        Pagination pagination = new Pagination(0, 1, 10);

        assertEquals(0, pagination.getTotalPages());
    }

    @Test
    void shouldCreateAsEmpty() {
        Pagination pagination = new Pagination();

        assertEquals(0, pagination.getTotalPages());
        assertEquals(1, pagination.getPage());
        assertEquals(0, pagination.getTotal());
        assertEquals(10, pagination.getRowsPerPage());
    }
}