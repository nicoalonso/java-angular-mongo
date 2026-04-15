package com.lacedorium.library.domain.user;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void shouldCreate() {
        User user = new User("John", "jdoe@gmail.com", List.of("admin"));

        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("jdoe@gmail.com", user.getDisplayName());
        assertEquals(List.of("admin"), user.getGroups());
        assertTrue(user.isAdmin());
    }
}