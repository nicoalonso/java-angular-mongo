package com.lacedorium.library.application.author.creator;

import com.lacedorium.library.domain.author.exception.InvalidBirthDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorCreatePayloadTest {
    FixtureBuilder<AuthorCreatePayload> builder;

    @BeforeEach
    void setUp() {
        builder = new FixtureBuilder<>(AuthorCreatePayload.class, "author");
    }

    @Test
    void shouldFailWhenBirthDateIsNull() {
        AuthorCreatePayload payload = builder.with("birthDate", "").build();

        assertThrows(InvalidBirthDateException.class, payload::getBirthDate);
    }

    @Test
    void shouldFailWhenDeathDateIsNull() {
        AuthorCreatePayload payload = builder.with("deathDate", "").build();

        assertNull(payload.getDeathDate());
    }
}
