package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.creator.ProviderCreate;
import com.lacedorium.library.application.provider.creator.ProviderCreatePayload;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.provider.ProviderReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderCreateControllerTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderCreateController controller;
    private FixtureBuilder<ProviderCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        ProviderCreate creator = new ProviderCreate(repoProvider, repoUser);
        controller = new ProviderCreateController(creator);

        fixture = new FixtureBuilder<>(ProviderCreatePayload.class, "provider");
    }

    @Test
    void shouldRunWhenCreate() {
        ProviderCreatePayload payload = fixture.build();

        ProviderReadView result = controller.create(payload);

        assertEquals(payload.name(), result.getData().name());
        repoProvider.assertStored();
    }
}