package com.lacedorium.library.application.provider.reader;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.ProviderRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderReadTest {
    private ProviderRepositoryStub repoProvider;
    private ProviderRead reader;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        reader = new ProviderRead(repoProvider);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                ProviderNotFoundException.class,
                () -> reader.dispatch("not-found")
        );
    }

    @Test
    void shouldRunWhenRead() {
        Provider provider = repoProvider.put(Ref.ProviderBestBuy);

        Provider result = reader.dispatch(provider.getId());

        assertEquals(provider, result);
    }
}