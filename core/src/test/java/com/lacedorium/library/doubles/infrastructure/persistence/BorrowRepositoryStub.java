package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowRepository;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.BorrowMother;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public class BorrowRepositoryStub
        extends EntityRepositoryStub<Borrow>
        implements BorrowRepository {
    private final CustomerRepositoryStub repoCustomer;

    public BorrowRepositoryStub() {
        repoCustomer = new CustomerRepositoryStub();
        super();
    }

    public BorrowRepositoryStub(CustomerRepositoryStub repoCustomer) {
        this.repoCustomer = repoCustomer;
        super();
    }

    @SneakyThrows
    @Override
    public Optional<Borrow> obtainByNumber(String number) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public List<Borrow> obtainByCustomer(String customerId) {
        throwException();
        return list;
    }

    @Override
    public List<Borrow> obtainByCustomer(String customerId, Integer limit) {
        return list;
    }

    @SneakyThrows
    @Override
    public List<Borrow> obtainByOverdue() {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Customer johnDoe = repoCustomer.get(Ref.CustomerJohnDoe);

        Borrow johnDoeBorrow = BorrowMother.johnDoe()
                .with("customer", johnDoe)
                .build();
        addFixture(Ref.BorrowJohnDoe, johnDoeBorrow);
    }
}
