package com.lacedorium.library.doubles.infrastructure.persistence;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookRepository;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.BookMother;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public class BookRepositoryStub
        extends EntityRepositoryStub<Book>
        implements BookRepository {
    private final AuthorRepositoryStub repoAuthor;
    private final EditorialRepositoryStub repoEditorial;

    public BookRepositoryStub() {
        repoAuthor = new AuthorRepositoryStub();
        repoEditorial = new EditorialRepositoryStub();
        super();
    }

    public BookRepositoryStub(AuthorRepositoryStub repoAuthor, EditorialRepositoryStub repoEditorial) {
        this.repoAuthor = repoAuthor;
        this.repoEditorial = repoEditorial;
        super();
    }

    public BookRepositoryStub(AuthorRepositoryStub repoAuthor) {
        this.repoAuthor = repoAuthor;
        this.repoEditorial = new EditorialRepositoryStub();
        super();
    }

    public BookRepositoryStub(EditorialRepositoryStub repoEditorial) {
        this.repoAuthor = new AuthorRepositoryStub();
        this.repoEditorial = repoEditorial;
        super();
    }

    @SneakyThrows
    @Override
    public Optional<Book> obtainByTitle(String title) {
        throwException();
        return Optional.ofNullable(read);
    }

    @SneakyThrows
    @Override
    public List<Book> obtainByAuthor(String authorId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<Book> obtainByAuthor(String authorId, int limit) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<Book> obtainByEditorial(String editorialId) {
        throwException();
        return list;
    }

    @SneakyThrows
    @Override
    public List<Book> obtainByEditorial(String editorialId, int limit) {
        throwException();
        return list;
    }

    @Override
    protected void makeFixtures() {
        Author shakespeare = repoAuthor.get(Ref.AuthorShakespeare);
        Author cervantes = repoAuthor.get(Ref.AuthorCervantes);
        Editorial anaya = repoEditorial.get(Ref.EditorialAnaya);

        Book bookRomeoAndJuliet = BookMother.romeoAndJuliet()
                .with("author", shakespeare)
                .with("editorial", anaya)
                .build();
        addFixture(Ref.BookRomeoAndJuliet, bookRomeoAndJuliet);

        Book bookDonQuixote = BookMother.donQuixote()
                .with("author", cervantes)
                .with("editorial", anaya)
                .build();
        addFixture(Ref.BookDonQuixote, bookDonQuixote);
    }
}
