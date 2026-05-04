package com.lacedorium.library.infrastructure.controller.v1.provider;

import com.lacedorium.library.application.provider.eraser.ProviderDelete;
import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderDeleteControllerTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderDeleteController controller;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        PurchaseRepositoryStub repoPurchase = new PurchaseRepositoryStub(repoProvider);
        ProviderDelete deleter = new ProviderDelete(repoProvider, repoPurchase);
        controller = new ProviderDeleteController(deleter);
    }

    @Test
    void shouldDelete() {
        Provider provider = repoProvider.put(Ref.ProviderAmazon);

        controller.deleteProvider(provider.getId());

        String providerId = repoProvider.assertRemoved();
        assertEquals(provider.getId(), providerId);
    }
}