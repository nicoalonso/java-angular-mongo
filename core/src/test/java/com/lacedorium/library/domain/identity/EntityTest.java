package com.lacedorium.library.domain.identity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    @Test
    public void shouldCreateEntityWithUser() {
        Entity entity = new Entity("testUser");

        assertNotNull(entity);
        assertEquals("testUser", entity.getCreatedBy());
        assertNotNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedBy());
        assertNull(entity.getUpdatedAt());
    }

    @Test
    public void shouldUpdateEntityWithUser() {
        Entity entity = new Entity("testUser");
        entity.updated("testUser2");

        assertNotNull(entity.getUpdatedBy());
        assertNotNull(entity.getUpdatedAt());
        assertEquals("testUser2", entity.getUpdatedBy());
        assertTrue(entity.getUpdatedAt().isAfter(entity.getCreatedAt()));
    }

    @Test
    public void shouldCreateWithId() {
        Entity entity = new Entity("1234567890", "testUser");

        assertNotNull(entity);
        assertEquals("1234567890", entity.getId());
        assertEquals("testUser", entity.getCreatedBy());
        assertNotNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedBy());
        assertNull(entity.getUpdatedAt());
    }
}
