package com.lacedorium.library.application.author.updater;

import com.lacedorium.library.domain.author.exception.InvalidBirthDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorUpdatePayloadTest {
    FixtureBuilder<AuthorUpdatePayload> builder;

    @BeforeEach
    void setUp() {
        builder = new FixtureBuilder<>(AuthorUpdatePayload.class, "author");
    }

    @Test
    void shouldFailWhenBirthDateIsNull() {
        AuthorUpdatePayload payload = builder.with("birthDate", "").build();

        assertThrows(InvalidBirthDateException.class, payload::getBirthDate);
    }

    @Test
    void shouldFailWhenDeathDateIsNull() {
        AuthorUpdatePayload payload = builder.with("deathDate", "").build();

        assertNull(payload.getDeathDate());
    }
}