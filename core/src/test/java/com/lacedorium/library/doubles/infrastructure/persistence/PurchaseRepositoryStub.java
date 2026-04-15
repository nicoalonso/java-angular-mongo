package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.PurchaseMother;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PurchaseRepositoryStub
        extends EntityRepositoryStub<Purchase>
        implements PurchaseRepository {
    private final ProviderRepositoryStub repoProvider;

    public PurchaseRepositoryStub() {
        repoProvider = new ProviderRepositoryStub();
        super();
    }

    public PurchaseRepositoryStub(ProviderRepositoryStub repoProvider) {
        this.repoProvider = repoProvider;
        super();
    }

    @SneakyThrows
    @Override
    public Optional<Purchase> obtainByProviderAndNumber(String providerId, String invoiceNumber) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public List<Purchase> obtainByProvider(String providerId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<Purchase> obtainByProvider(String providerId, int limit) {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Provider amazon = repoProvider.get(Ref.ProviderAmazon);
        Provider bestBuy = repoProvider.get(Ref.ProviderBestBuy);

        Purchase amazonInv1Purchase = PurchaseMother.amazonInvoice1()
                .with("provider", amazon)
                .build();
        addFixture(Ref.PurchaseAmazonInv1, amazonInv1Purchase);

        Purchase bestBuyInv2Purchase = PurchaseMother.bestBuyInvoice2()
                .with("provider", bestBuy)
                .build();
        addFixture(Ref.PurchaseBestBuyInv2, bestBuyInv2Purchase);
    }
}
