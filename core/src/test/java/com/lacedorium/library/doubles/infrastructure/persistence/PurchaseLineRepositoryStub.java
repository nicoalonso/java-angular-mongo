package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.PurchaseLineRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.PurchaseLineMother;
import lombok.SneakyThrows;

import java.util.List;

public class PurchaseLineRepositoryStub
        extends EntityRepositoryStub<PurchaseLine>
        implements PurchaseLineRepository {
    private final PurchaseRepositoryStub repoPurchase;
    private final BookRepositoryStub repoBook;

    public PurchaseLineRepositoryStub() {
        repoPurchase = new PurchaseRepositoryStub();
        repoBook = new BookRepositoryStub();
        super();
    }

    public PurchaseLineRepositoryStub(PurchaseRepositoryStub repoPurchase) {
        this.repoPurchase = repoPurchase;
        repoBook = new BookRepositoryStub();
        super();
    }

    public PurchaseLineRepositoryStub(BookRepositoryStub repoBook) {
        repoPurchase = new PurchaseRepositoryStub();
        this.repoBook = repoBook;
        super();
    }

    public PurchaseLineRepositoryStub(PurchaseRepositoryStub repoPurchase, BookRepositoryStub repoBook) {
        this.repoPurchase = repoPurchase;
        this.repoBook = repoBook;
        super();
    }

    @SneakyThrows
    @Override
    public List<PurchaseLine> obtainByPurchase(String purchaseId) {
        return list;
    }

    @SneakyThrows
    @Override
    public List<PurchaseLine> obtainByBook(String bookId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<PurchaseLine> obtainByBook(String bookId, int limit) {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Purchase amazonInv1Purchase = repoPurchase.get(Ref.PurchaseAmazonInv1);
        Purchase bestBuyInv2Purchase = repoPurchase.get(Ref.PurchaseBestBuyInv2);

        Book bookRomeoAndJuliet = repoBook.get(Ref.BookRomeoAndJuliet);
        Book bookDonQuixote = repoBook.get(Ref.BookDonQuixote);

        PurchaseLine amazonLine1 = PurchaseLineMother.amazonLine1()
                .with("purchase", amazonInv1Purchase)
                .with("book", bookRomeoAndJuliet)
                .build();
        addFixture(Ref.PurchaseLineAmazonLine1, amazonLine1);

        PurchaseLine amazonLine2 = PurchaseLineMother.amazonLine2()
                .with("purchase", amazonInv1Purchase)
                .with("book", bookDonQuixote)
                .build();
        addFixture(Ref.PurchaseLineAmazonLine2, amazonLine2);

        PurchaseLine bestBuyLine1 = PurchaseLineMother.bestBuyLine1()
                .with("purchase", bestBuyInv2Purchase)
                .with("book", bookRomeoAndJuliet)
                .build();
        addFixture(Ref.PurchaseLineBestBuyLine1, bestBuyLine1);
    }
}
