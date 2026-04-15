package com.lacedorium.library.application.provider.creator;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderCreateTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderCreate creator;
    private FixtureBuilder<ProviderCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        creator = new ProviderCreate(repoProvider, repoUser);

        fixture = new FixtureBuilder<>(ProviderCreatePayload.class, "provider");
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoProvider.put(Ref.ProviderAmazon);

        ProviderCreatePayload payload = fixture.build();

        assertThrows(
                ProviderAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        ProviderCreatePayload payload = fixture.build();

        Provider provider = creator.dispatch(payload);

        assertEquals(payload.name(), provider.getName());
        repoProvider.assertStored();
    }
}