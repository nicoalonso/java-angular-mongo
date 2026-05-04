package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.updater.ProviderUpdate;
import com.lacedorium.library.application.provider.updater.ProviderUpdatePayload;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderUpdateControllerTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderUpdateController controller;
    private FixtureBuilder<ProviderUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        ProviderUpdate updater = new ProviderUpdate(repoProvider, repoUser);
        controller = new ProviderUpdateController(updater);

        fixture = new FixtureBuilder<>(ProviderUpdatePayload.class, "provider");
    }

    @Test
    void shouldUpdate() {
        Provider provider = repoProvider.put(Ref.ProviderAmazon);
        ProviderUpdatePayload payload = fixture.build();

        controller.update(provider.getId(), payload);

        Provider stored = repoProvider.assertStored();
        assertEquals(payload.name(), stored.getName());
    }
}