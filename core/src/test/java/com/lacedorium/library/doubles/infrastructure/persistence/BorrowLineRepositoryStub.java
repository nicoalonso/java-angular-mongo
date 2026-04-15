package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.domain.borrow.BorrowLineRepository;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.BorrowLineMother;
import lombok.SneakyThrows;

import java.util.List;

public class BorrowLineRepositoryStub
        extends EntityRepositoryStub<BorrowLine>
        implements BorrowLineRepository {
    private final BorrowRepositoryStub repoBorrow;
    private final BookRepositoryStub repoBook;

    public BorrowLineRepositoryStub() {
        repoBorrow = new BorrowRepositoryStub();
        repoBook = new BookRepositoryStub();
        super();
    }

    public BorrowLineRepositoryStub(BookRepositoryStub repoBook) {
        repoBorrow = new BorrowRepositoryStub();
        this.repoBook = repoBook;
        super();
    }

    public BorrowLineRepositoryStub(BorrowRepositoryStub repoBorrow) {
        this.repoBorrow = repoBorrow;
        this.repoBook = new BookRepositoryStub();
        super();
    }

    public BorrowLineRepositoryStub(BorrowRepositoryStub repoBorrow, BookRepositoryStub repoBook) {
        this.repoBorrow = repoBorrow;
        this.repoBook = repoBook;
        super();
    }

    @SneakyThrows
    @Override
    public List<BorrowLine> obtainByBorrow(String borrowId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<BorrowLine> obtainByBook(String bookId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<BorrowLine> obtainByBook(String bookId, Integer limit) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<BorrowLine> obtainActiveByBook(String bookId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    protected void makeFixtures() {
        Borrow johnDoeBorrow = repoBorrow.get(Ref.BorrowJohnDoe);
        Book bookRomeoAndJuliet = repoBook.get(Ref.BookRomeoAndJuliet);
        Book bookQuixote = repoBook.get(Ref.BookDonQuixote);

        BorrowLine johnDoeRomeoBorrowLine = BorrowLineMother.romeoAndJuliet()
                .with("borrow", johnDoeBorrow)
                .with("book", bookRomeoAndJuliet)
                .build();
        addFixture(Ref.BorrowLineJohnRomeoAndJuliet, johnDoeRomeoBorrowLine);

        BorrowLine johnDoeQuixoteBorrowLine = BorrowLineMother.donQuixote()
                .with("borrow", johnDoeBorrow)
                .with("book", bookQuixote)
                .build();
        addFixture(Ref.BorrowLineJohnQuixote, johnDoeQuixoteBorrowLine);
    }
}
