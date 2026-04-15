package com.lacedorium.library.application.provider.updater;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderUpdateTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderUpdate updater;
    private FixtureBuilder<ProviderUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        updater = new ProviderUpdate(repoProvider, repoUser);

        fixture = new FixtureBuilder<>(ProviderUpdatePayload.class, "provider");
    }

    @Test
    void shouldFailWhenNotFound() {
        ProviderUpdatePayload payload = fixture.build();

        assertThrows(
                ProviderNotFoundException.class,
                () -> updater.dispatch("not-found", payload)
        );
    }

    @Test
    void shouldRunWhenModify() {
        Provider provider = repoProvider.put(Ref.ProviderAmazon);
        ProviderUpdatePayload payload = fixture.build();

        updater.dispatch(provider.getId(), payload);

        repoProvider.assertStored();
    }
}