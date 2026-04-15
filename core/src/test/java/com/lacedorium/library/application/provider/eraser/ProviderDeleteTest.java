package com.lacedorium.library.application.provider.eraser;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderDeleteTest {
    private ProviderRepositoryStub repoProvider;
    private PurchaseRepositoryStub repoPurchase;
    private ProviderDelete deleter;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        repoPurchase = new PurchaseRepositoryStub(repoProvider);
        deleter = new ProviderDelete(repoProvider, repoPurchase);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                ProviderNotFoundException.class,
                () -> deleter.dispatch("not-found")
        );
    }

    @Test
    void shouldFailWhenAssociated() {
        Provider provider = repoProvider.put(Ref.ProviderAmazon);
        repoPurchase.attach(Ref.PurchaseAmazonInv1);

        assertThrows(
                ProviderAssociatedException.class,
                () -> deleter.dispatch(provider.getId())
        );
    }

    @Test
    void shouldRunWhenDelete() {
        Provider provider = repoProvider.put(Ref.ProviderAmazon);

        deleter.dispatch(provider.getId());

        repoProvider.assertRemoved();
    }
}