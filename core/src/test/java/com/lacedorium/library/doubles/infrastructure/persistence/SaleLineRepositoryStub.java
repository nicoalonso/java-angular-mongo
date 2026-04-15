package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.domain.sale.SaleLineRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.SaleLineMother;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class SaleLineRepositoryStub
        extends EntityRepositoryStub<SaleLine>
        implements SaleLineRepository {
    private final SaleRepositoryStub repoSale;
    private final BookRepositoryStub repoBook;

    public SaleLineRepositoryStub() {
        repoSale = new SaleRepositoryStub();
        repoBook = new BookRepositoryStub();
        super();
    }

    public SaleLineRepositoryStub(SaleRepositoryStub repoSale) {
        this.repoSale = repoSale;
        repoBook = new BookRepositoryStub();
        super();
    }

    public SaleLineRepositoryStub(BookRepositoryStub repoBook) {
        repoSale = new SaleRepositoryStub();
        this.repoBook = repoBook;
        super();
    }

    @SneakyThrows
    @Override
    public List<SaleLine> obtainBySale(String saleId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<SaleLine> obtainByBook(String bookId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<SaleLine> obtainByBook(String bookId, Integer limit) {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Sale johnDoeSale1 = repoSale.get(Ref.SaleJohnDoe1);
        Sale johnDoeSale2 = repoSale.get(Ref.SaleJohnDoe2);

        Book bookRomeoAndJuliet = repoBook.get(Ref.BookRomeoAndJuliet);
        Book bookDonQuixote = repoBook.get(Ref.BookDonQuixote);

        SaleLine johnDoeSale1Line1 = SaleLineMother.johnDoeSale1Line1()
                .with("sale", johnDoeSale1)
                .with("book", bookRomeoAndJuliet)
                .build();
        addFixture(Ref.SaleLineJohnDoe1Line1, johnDoeSale1Line1);

        SaleLine johnDoeSale1Line2 = SaleLineMother.johnDoeSale1Line2()
                .with("sale", johnDoeSale1)
                .with("book", bookDonQuixote)
                .build();
        addFixture(Ref.SaleLineJohnDoe1Line2, johnDoeSale1Line2);

        SaleLine johnDoeSale2Line1 = SaleLineMother.johnDoeSale2Line1()
                .with("sale", johnDoeSale2)
                .with("book", bookRomeoAndJuliet)
                .build();
        addFixture(Ref.SaleLineJohnDoe2Line1, johnDoeSale2Line1);
    }
}
