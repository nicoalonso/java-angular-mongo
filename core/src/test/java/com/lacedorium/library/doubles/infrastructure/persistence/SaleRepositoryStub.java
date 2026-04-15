package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.SaleMother;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SaleRepositoryStub
        extends EntityRepositoryStub<Sale>
        implements SaleRepository {
    private final CustomerRepositoryStub repoCustomer;

    public SaleRepositoryStub() {
        repoCustomer = new CustomerRepositoryStub();
        super();
    }

    public SaleRepositoryStub(CustomerRepositoryStub repoCustomer) {
        this.repoCustomer = repoCustomer;
        super();
    }

    @SneakyThrows
    @Override
    public Optional<Sale> obtainByNumber(String number) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public List<Sale> obtainByCustomer(String customerId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<Sale> obtainByCustomer(String customerId, Integer limit) {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Customer johnDoe = repoCustomer.get(Ref.CustomerJohnDoe);

        Sale johnDoeSale1 = SaleMother.johnDoeSale1()
                .with("customer", johnDoe)
                .build();
        addFixture(Ref.SaleJohnDoe1, johnDoeSale1);

        Sale johnDoeSale2 = SaleMother.johnDoeSale2()
                .with("customer", johnDoe)
                .build();
        addFixture(Ref.SaleJohnDoe2, johnDoeSale2);
    }
}
