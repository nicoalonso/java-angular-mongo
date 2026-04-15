package com.lacedorium.library.domain.identity;

import com.lacedorium.library.domain.identity.exception.IdEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityTest {
    @Test
    void shouldThrowIdEmptyExceptionWhenIdIsNull() {
        assertThrows(IdEmptyException.class, () -> new Identity(null));
    }

    @Test
    void shouldThrowIdEmptyExceptionWhenIdIsEmpty() {
        assertThrows(IdEmptyException.class, () -> new Identity(""));
    }

    @Test
    void shouldCreatedEntityWithId() {
        Identity entity = new Identity();
        entity.initialize();

        assertNotNull(entity.getId());
    }

    @Test
    void shouldCreateWhenAssignedId() {
        Identity entity = new Identity("1234564897");

        assertEquals("1234564897", entity.getId());
    }
}
