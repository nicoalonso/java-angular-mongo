package com.lacedorium.library.domain.identity.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldOptionsTest {
    @Test
    void shouldCreate() {
        FieldOptions options = new FieldOptions();

        assertNotNull(options);
        assertTrue(options.canSelect());
        assertTrue(options.canFilter());
        assertTrue(options.canSort());
    }

    @Test
    void shouldDisableCanSelect() {
        FieldOptions options = new FieldOptions();

        options.add(FieldOption.NO_SELECT);

        assertFalse(options.canSelect());
    }

    @Test
    void shouldDisableCanFilter() {
        FieldOptions options = new FieldOptions();

        options.add(FieldOption.NO_FILTER);

        assertFalse(options.canFilter());
    }

    @Test
    void shouldDisableCanSort() {
        FieldOptions options = new FieldOptions();

        options.add(FieldOption.NO_SORT);

        assertFalse(options.canSort());
    }
}
