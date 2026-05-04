package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.reader.ProviderRead;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.provider.ProviderReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderReadControllerTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderReadController controller;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        ProviderRead reader = new ProviderRead(repoProvider);
        controller = new ProviderReadController(reader);
    }

    @Test
    void shouldRead() {
        Provider provider = repoProvider.put(Ref.ProviderBestBuy);

        ProviderReadView result = controller.read(provider.getId());

        assertEquals(provider.getId(), result.getData().id());
    }
}