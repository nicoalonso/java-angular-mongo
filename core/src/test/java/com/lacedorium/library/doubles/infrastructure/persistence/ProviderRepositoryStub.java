package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.provider.ProviderRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.ProviderMother;
import lombok.SneakyThrows;

import java.util.Optional;

public class ProviderRepositoryStub
        extends EntityRepositoryStub<Provider>
        implements ProviderRepository {
    @SneakyThrows
    @Override
    public Optional<Provider> obtainByName(String name) {
        return Optional.ofNullable(read);
    }

    @Override
    protected void makeFixtures() {
        Provider amazon = ProviderMother.amazon().build();
        addFixture(Ref.ProviderAmazon, amazon);

        Provider bestBuy = ProviderMother.bestBuy().build();
        addFixture(Ref.ProviderBestBuy, bestBuy);
    }
}
